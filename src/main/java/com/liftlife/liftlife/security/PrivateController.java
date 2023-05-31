package com.liftlife.liftlife.security;

import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivateController {
    @Autowired
    private AuthService authService;

    @GetMapping("/private")
    public String privateEndpoint() throws FirebaseAuthException {
        return "Hello, " + AuthService.getCurrentUserAuthId() + "! This is a private endpoint.";
    }
}
