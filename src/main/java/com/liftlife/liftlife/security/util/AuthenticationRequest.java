package com.liftlife.liftlife.security.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuthenticationRequest {
    private String userId;
    private String token;

}
