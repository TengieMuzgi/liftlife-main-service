package com.liftlife.liftlife.security;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class FirebaseTokenProvider {

    private FirebaseApp firebaseApp;

    public FirebaseToken verifyToken(String token) {
        FirebaseToken decodedToken = null;
        try {
            decodedToken = FirebaseAuth.getInstance(firebaseApp).verifyIdToken(token);
        } catch (FirebaseAuthException e) {
            throw new RuntimeException(e);
        }
        return decodedToken;
    }
}

