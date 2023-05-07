package com.liftlife.liftlife.security.util;

import com.liftlife.liftlife.common.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
public class RegisterRequest {
    private String email;
    private String password;
    private String passwordRepeated;
    private UserRole role;
    private Date registerDate;

    public RegisterRequest(String email, String password, String passwordRepeated, UserRole role) {
        this.email = email;
        this.password = password;
        this.passwordRepeated = passwordRepeated;
        this.role = role;
        this.registerDate = new Date();
    }

    public RegisterRequest() {
        this.registerDate = new Date();
    }
}
