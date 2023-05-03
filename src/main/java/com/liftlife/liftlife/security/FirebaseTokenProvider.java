package com.liftlife.liftlife.security;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class FirebaseTokenProvider {


    public FirebaseToken verifyToken(String token) {
        FirebaseToken decodedToken = null;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        } catch (FirebaseAuthException e) {
            throw new RuntimeException(e);
        }
        return decodedToken;
    }

//    public String generateToken(Authentication authentication) {
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        userDetails.get
//
//        Instant now = Instant.now();
//        Instant expiryDate = now.plus(Duration.ofDays(1));
//
//
//        Map<String, Object> additionalClaims = new HashMap<String, Object>();
//        String customToken = FirebaseAuth.getInstance().createCustomToken();
//
//        return "";
//    }
}

