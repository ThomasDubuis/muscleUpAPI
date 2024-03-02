package com.ynov.muscleup.service;


import com.ynov.muscleup.config.JwtService;
import com.ynov.muscleup.model.BaseResponse;
import com.ynov.muscleup.model.Customer;
import com.ynov.muscleup.model.auth.*;
import com.ynov.muscleup.model.customer_args.Role;
import com.ynov.muscleup.model.customer_args.Visibility;
import com.ynov.muscleup.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class AuthenticationServiceTest {
    @InjectMocks
    AuthenticationService authenticationService;
    @Mock
    CustomerRepository customerRepository;
    @Mock
    JwtService jwtService;
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    PasswordEncoder passwordEncoder;

    private final String TOKEN = "Tokenazeazeaq13512fazr";
    private final Customer USER = new Customer("1", "Joe", "LeJoe", "JoeLeJoe@gmail.com", "passwordHashed", Role.USER, Visibility.ALL);


    @Test
    void registerButEmailAlreadyUsed() {
        RegisterRequest request = RegisterRequest.builder().email("emailAlreadyExist@gmail.com").build();
        when(customerRepository.findByEmail("emailAlreadyExist@gmail.com")).thenReturn(Optional.of(new Customer()));

        ResponseEntity<BaseResponse<AuthenticationResponse>> response = authenticationService.register(request);

        assertFalse(Objects.requireNonNull(response.getBody()).getSuccess());
        assertEquals("Email already used: emailAlreadyExist@gmail.com", response.getBody().getErrorMessage());
    }

    @Test
    void registerShouldReturnToken() {
        RegisterRequest request = new RegisterRequest("Joe", "LeJoe", "JoeLeJoe@gmail.com", "password", "password");
        when(customerRepository.findByEmail("JoeLeJoe@gmail.com")).thenReturn(Optional.empty());
        when(jwtService.generateToken(any())).thenReturn(TOKEN);

        ResponseEntity<BaseResponse<AuthenticationResponse>> response = authenticationService.register(request);

        verify(customerRepository, times(1)).save(any());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(TOKEN, Objects.requireNonNull(response.getBody()).getResult().getToken());
    }

    @Test
    void authenticateShouldReturnToken() {
        AuthenticationRequest request = new AuthenticationRequest("JoeLeJoe@gmail.com", "password");
        when(customerRepository.findByEmail("JoeLeJoe@gmail.com")).thenReturn(Optional.of(USER));
        when(jwtService.generateToken(any())).thenReturn(TOKEN);

        AuthenticationResponse response = authenticationService.authenticate(request);

        assertEquals(TOKEN, response.getToken());
    }

    @Test
    void changePasswordButCustomerNotFound() {
        PasswordChangeRequest request = new PasswordChangeRequest("emailNotFound@gmail.com", "oldPassword", "oldPassword", "newPassword", "newPassword");
        when(customerRepository.findByEmail("emailNotFound@gmail.com")).thenReturn(Optional.empty());

        PasswordChangeResponse response = authenticationService.changePassword(request);

        assertFalse(response.isPasswordChanged());
        assertEquals("Email not used: emailNotFound@gmail.com", response.getErrorMessage());
    }

    @Test
    void changePasswordButEmailAndPasswordDontMatch() {
        PasswordChangeRequest request = new PasswordChangeRequest("JoeLeJoe@gmail.com", "oldPassword", "oldPassword", "newPassword", "newPassword");
        when(customerRepository.findByEmail("JoeLeJoe@gmail.com")).thenReturn(Optional.of(USER));
        when(passwordEncoder.matches(request.getOldPassword(), USER.getHashedPassword())).thenReturn(false);

        PasswordChangeResponse response = authenticationService.changePassword(request);

        assertFalse(response.isPasswordChanged());
        assertEquals("Email and password do not match", response.getErrorMessage());
    }

    @Test
    void changePasswordShouldReturnPasswordChangedTrue() {
        PasswordChangeRequest request = new PasswordChangeRequest("JoeLeJoe@gmail.com", "oldPassword", "oldPassword", "newPassword", "newPassword");
        when(customerRepository.findByEmail("JoeLeJoe@gmail.com")).thenReturn(Optional.of(USER));
        when(passwordEncoder.matches(request.getOldPassword(), USER.getHashedPassword())).thenReturn(true);

        PasswordChangeResponse response = authenticationService.changePassword(request);

        assertTrue(response.isPasswordChanged());
        verify(customerRepository, times(1)).save(any());
    }

}