package com.liftlife.liftlife.security;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivateController {
    @GetMapping("/private")
    public String privateEndpoint() throws FirebaseAuthException {
        UserRecord userRecord = AuthService.getCurrentUser();
        System.out.println(userRecord.getUid());
        return "Hello, " + userRecord.getUid() + "! This is a private endpoint.";
    }
}
