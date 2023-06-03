package com.liftlife.liftlife.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PublicController {
    @Autowired
    private AuthService authService;

    @Autowired
    private FirebaseAuth firebaseAuth;

    @GetMapping("/public")
    public void privateEndpoint() throws FirebaseAuthException {

        String url = firebaseAuth.getUser("MuA1nRxWA2OOpS0pNOSpZxMcKan2").getPhotoUrl();
        log.info(url);
//        return "Hello, " + AuthService.getCurrentUserAuthId() + "! This is a private endpoint.";
    }
}
