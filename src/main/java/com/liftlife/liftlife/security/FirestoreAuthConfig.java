package com.liftlife.liftlife.security;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.StorageClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.io.IOException;

@Configuration
@DependsOn("firestoreConnector")
public class FirestoreAuthConfig {
    @Bean
    public FirebaseAuth firebaseAuth() throws IOException {
        return FirebaseAuth.getInstance();
    }

    @Bean
    public Bucket firebaseBucket() {
        // Instantiates a client
//        Storage storage = StorageOptions.getDefaultInstance().getService();
        return StorageClient.getInstance().bucket();
    }
}
