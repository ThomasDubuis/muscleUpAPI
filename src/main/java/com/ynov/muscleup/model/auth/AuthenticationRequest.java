package com.ynov.muscleup.model.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    private String email;
    private String password;

    public boolean isAllArgsFill() {
        if (email == null || email.isBlank()){
            return false;
        }else if (password == null || password.isBlank()){
            return false;
        }else {
            return true;
        }
    }
}
