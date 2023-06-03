package com.liftlife.liftlife.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivateController {
    @Autowired
    private AuthService authService;

    @Autowired
    private FirebaseAuth firebaseAuth;

    @GetMapping("/private")
    public String privateEndpoint() throws FirebaseAuthException {
        System.out.println(firebaseAuth.getUser("mPpm9E3vCURi0tBfJH8IfgvGKTp2").getPhotoUrl());
        return "Hello, " + AuthService.getCurrentUserAuthId() + "! This is a private endpoint.";
    }
}
