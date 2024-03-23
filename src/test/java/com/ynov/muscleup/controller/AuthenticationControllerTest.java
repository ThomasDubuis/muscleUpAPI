package com.ynov.muscleup.controller;

import com.ynov.muscleup.model.BaseResponse;
import com.ynov.muscleup.model.auth.*;
import com.ynov.muscleup.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class AuthenticationControllerTest {
    private static final String ALL_ARG_NOT_PROVIDED = "All args are not provided";
    private static final String PASSWORD_NOT_SAME = "Password are not same";
    @InjectMocks
    AuthenticationController authenticationController;

    @Mock
    AuthenticationService authenticationService;

    @Test
    void registerWithMissingFieldShouldReturnBadRequest() {
        RegisterRequest request = new RegisterRequest("Joe", null, "JoeLeJoe@gmail.com", "pwd", "pwd");

        ResponseEntity<BaseResponse<AuthenticationResponse>> response = authenticationController.register(request);

        assertFalse(Objects.requireNonNull(response.getBody()).getSuccess());
        assertEquals(ALL_ARG_NOT_PROVIDED, response.getBody().getErrorMessage());
    }

    @Test
    void registerWithDifferentPasswordShouldReturnBadRequest() {
        RegisterRequest request = new RegisterRequest("Joe", "LeJoe", "JoeLeJoe@gmail.com", "pwd", "pwd2");

        ResponseEntity<BaseResponse<AuthenticationResponse>> response = authenticationController.register(request);

        assertFalse(Objects.requireNonNull(response.getBody()).getSuccess());
        assertEquals(PASSWORD_NOT_SAME, response.getBody().getErrorMessage());
    }

    @Test
    void registerShouldReturnResponse200AndValidToken() {
        RegisterRequest request = new RegisterRequest("Joe", "LeJoe", "JoeLeJoe@gmail.com", "pwd", "pwd");
        Mockito.when(authenticationService.register(request)).thenReturn(BaseResponse.ok(new AuthenticationResponse("", "tokenValid")));

        ResponseEntity<BaseResponse<AuthenticationResponse>> response = authenticationController.register(request);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("tokenValid", Objects.requireNonNull(response.getBody()).getResult().getToken());
    }

    @Test
    void authenticateWithMissingFieldShouldReturnBadRequest() {
        AuthenticationRequest request = new AuthenticationRequest("JoeLeJoe@gmail.com", null);

        ResponseEntity<BaseResponse<AuthenticationResponse>> response = authenticationController.authenticate(request);

        assertFalse(Objects.requireNonNull(response.getBody()).getSuccess());
        assertEquals(ALL_ARG_NOT_PROVIDED, response.getBody().getErrorMessage());
    }
    @Test
    void authenticateShouldReturnResponse200AndValidToken() {
        AuthenticationRequest request = new AuthenticationRequest("JoeLeJoe@gmail.com", "pwd");
        Mockito.when(authenticationService.authenticate(request)).thenReturn(BaseResponse.ok(new AuthenticationResponse("", "tokenValid")));

        ResponseEntity<BaseResponse<AuthenticationResponse>> response = authenticationController.authenticate(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("tokenValid", Objects.requireNonNull(response.getBody()).getResult().getToken());
    }

    @Test
    void changePasswordWithMissingFieldShouldReturnBadRequest() {
        PasswordChangeRequest request = new PasswordChangeRequest("JoeLeJoe@gmail.com", "pwd", "pwd", "pwdChanged", null);

        ResponseEntity<BaseResponse<PasswordChangeResponse>> response = authenticationController.changePassword(request);

        assertFalse(Objects.requireNonNull(response.getBody()).getSuccess());
        assertEquals(ALL_ARG_NOT_PROVIDED, response.getBody().getErrorMessage());
    }

    @Test
    void changePasswordWithDifferentOldPasswordShouldReturnBadRequest() {
        PasswordChangeRequest request = new PasswordChangeRequest("JoeLeJoe@gmail.com", "pwd", "pwd2", "pwdChanged", "pwdChanged");

        ResponseEntity<BaseResponse<PasswordChangeResponse>> response = authenticationController.changePassword(request);

        assertFalse(Objects.requireNonNull(response.getBody()).getSuccess());
        assertEquals(PASSWORD_NOT_SAME, response.getBody().getErrorMessage());
    }

    @Test
    void changePasswordWithDifferentNewPasswordShouldReturnBadRequest() {
        PasswordChangeRequest request = new PasswordChangeRequest("JoeLeJoe@gmail.com", "pwd", "pwd", "pwdChanged", "pwdChanged2");

        ResponseEntity<BaseResponse<PasswordChangeResponse>> response = authenticationController.changePassword(request);

        assertFalse(Objects.requireNonNull(response.getBody()).getSuccess());
        assertEquals(PASSWORD_NOT_SAME, response.getBody().getErrorMessage());
    }

    @Test
    void changePasswordShouldReturnResponse200AndValidResponse() {
        PasswordChangeRequest request = new PasswordChangeRequest("JoeLeJoe@gmail.com", "pwd", "pwd", "pwdChanged", "pwdChanged");
        Mockito.when(authenticationService.changePassword(request)).thenReturn(new PasswordChangeResponse(true, null));

        ResponseEntity<BaseResponse<PasswordChangeResponse>> response = authenticationController.changePassword(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(Objects.requireNonNull(response.getBody()).getErrorMessage());
        assertTrue(Objects.requireNonNull(response.getBody().getResult()).isPasswordChanged());
    }

}