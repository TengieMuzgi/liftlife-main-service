package com.liftlife.liftlife.util.database;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class FirestoreConnector {

    public FirestoreConnector() throws IOException {
        InputStream serviceAccount = getClass()
                .getResourceAsStream("/liftlife-firebase-adminsdk-connection-string.json");

        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .setStorageBucket("realtime-database-prototype.appspot.com")
                .build();
        FirebaseApp.initializeApp(options);
    }

}