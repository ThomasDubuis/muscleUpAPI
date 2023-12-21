package com.ynov.muscleup.controller;

import com.ynov.muscleup.model.auth.*;
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

    private static final String ALL_ARG_NOT_PROVIDED = "All args are not provided";
    private static final String PASSWORD_NOT_SAME = "Password are not same";

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        if (!request.isAllArgsFill() ) {
            logger.warn(ALL_ARG_NOT_PROVIDED);
            return ResponseEntity.badRequest().build();
        } else if (!request.isSamePassword()) {
            logger.warn(PASSWORD_NOT_SAME);
            return ResponseEntity.badRequest().build();
        }
        return authenticationService.register(request);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        if (!request.isAllArgsFill()){
            logger.warn(ALL_ARG_NOT_PROVIDED);
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/changepwd")
    public ResponseEntity<PasswordChangeResponse> changePassword(@RequestBody PasswordChangeRequest request) {
        if (!request.isAllArgsFill()){
            logger.warn(ALL_ARG_NOT_PROVIDED);
            return ResponseEntity.badRequest().body(PasswordChangeResponse.builder().passwordChanged(false).errorMessage(ALL_ARG_NOT_PROVIDED).build());
        }
        if (!request.checkOldPasswordAreSame() || !request.checkNewPasswordAreSame()) {
            logger.warn(PASSWORD_NOT_SAME);
            return ResponseEntity.badRequest().body(PasswordChangeResponse.builder().passwordChanged(false).errorMessage(PASSWORD_NOT_SAME).build());
        }
        return ResponseEntity.ok(authenticationService.changePassword(request));

    }

}
