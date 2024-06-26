package com.ynov.muscleup.model.auth;

import com.ynov.muscleup.model.customer_args.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String confirmPassword;
    private Gender gender;


    public boolean isAllArgsFill() {
        if (firstname == null || firstname.isBlank()){
            return false;
        }else if (lastname == null || lastname.isBlank()){
            return false;
        }else if (email == null || email.isBlank()){
            return false;
        }else if (password == null || password.isBlank()){
            return false;
        }else if (confirmPassword == null || confirmPassword.isBlank()){
            return false;
        }else if (gender == null){
            return false;
        }else {
            return true;
        }
    }

    public boolean isSamePassword() {
        return password.equals(confirmPassword);
    }

}
