package com.ynov.muscleup.service;

import com.ynov.muscleup.config.JwtService;
import com.ynov.muscleup.model.Customer;
import com.ynov.muscleup.model.auth.*;
import com.ynov.muscleup.model.customer_args.Role;
import com.ynov.muscleup.model.customer_args.Visibility;
import com.ynov.muscleup.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private static final Logger logger = LogManager.getLogger(AuthenticationService.class);

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<AuthenticationResponse> register(RegisterRequest request) {
        Optional<Customer> customer = customerRepository.findByEmail(request.getEmail());
        if (customer.isPresent()) {
            logger.error("Email already used: {}", request.getEmail());
            return ResponseEntity.badRequest().build();
        }

        var user = Customer.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .hashedPassword(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .visibility(Visibility.ALL)
                .build();
        customerRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return ResponseEntity.ok(AuthenticationResponse.builder().token(jwtToken).build());
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = customerRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public PasswordChangeResponse changePassword(PasswordChangeRequest request) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(request.getEmail());
        if (customerOptional.isEmpty()) {
            logger.error("Email not used: {}", request.getEmail());
            return PasswordChangeResponse.builder()
                    .passwordChanged(false)
                    .errorMessage("Email not used: " + request.getEmail())
                    .build();
        }
        Customer customer = customerOptional.get();
        if (passwordEncoder.matches(request.getOldPassword(), customer.getHashedPassword())) {
            customer.setHashedPassword(passwordEncoder.encode(request.getNewPassword()));
            customerRepository.save(customer);
            logger.debug("Password changed for Email : {}", customer.getEmail());
            return PasswordChangeResponse.builder().passwordChanged(true).build();

        }else {
            logger.error("Email and password do not match");
            return PasswordChangeResponse.builder().passwordChanged(false).errorMessage("Email and password do not match").build();
        }


    }
}
