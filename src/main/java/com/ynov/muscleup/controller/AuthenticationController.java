package com.ynov.muscleup.controller;

import com.ynov.muscleup.model.auth.AuthenticationRequest;
import com.ynov.muscleup.model.auth.AuthenticationResponse;
import com.ynov.muscleup.model.auth.RegisterRequest;
import com.ynov.muscleup.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private static final Logger logger = LogManager.getLogger(AuthenticationController.class);

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        if (!request.isAllArgsFill() ) {
            logger.error("All args are not provided");
            return ResponseEntity.badRequest().build();
        } else if (!request.isSamePassword()) {
            logger.error("Password are not same");
            return ResponseEntity.badRequest().build();
        }
        return authenticationService.register(request);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        if (!request.isAllArgsFill()){
            logger.error("All args are not provided");
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
