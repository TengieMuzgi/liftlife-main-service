package com.liftlife.liftlife.database;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class FirestoreConnector {
    public FirestoreConnector() throws IOException {
        InputStream serviceAccount =
                new FileInputStream("src/main/resources/liftlife-b89d1-firebase-adminsdk-1v4ox-c62b51cb58.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp.initializeApp(options);
    }

}
