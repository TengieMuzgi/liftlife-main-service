package com.liftlife.liftlife.security.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoginRequest {
    private String email;
    private String password;
}
