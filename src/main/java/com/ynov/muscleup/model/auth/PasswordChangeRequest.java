package com.ynov.muscleup.model.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChangeRequest {
    private String email;
    private String oldPassword;
    private String oldPasswordConfirm;
    private String newPassword;
    private String newPasswordConfirm;


    public boolean isAllArgsFill() {
        if (email == null || email.isBlank()) {
            return false;
        }else if (oldPassword == null || oldPassword.isBlank()){
            return false;
        }else if (oldPasswordConfirm == null || oldPasswordConfirm.isBlank()){
            return false;
        }else if (newPassword == null || newPassword.isBlank()){
            return false;
        }else if (newPasswordConfirm == null || newPasswordConfirm.isBlank()){
            return false;
        }else {
            return true;
        }
    }

    public boolean checkOldPasswordAreSame() {
        return oldPassword.equals(oldPasswordConfirm);
    }

    public boolean checkNewPasswordAreSame() {
        return newPassword.equals(newPasswordConfirm);
    }
}
