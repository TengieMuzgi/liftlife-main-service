package com.liftlife.liftlife.security.util;

import com.liftlife.liftlife.common.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String password;
    private String passwordRepeated;
    private UserRole role;
}
