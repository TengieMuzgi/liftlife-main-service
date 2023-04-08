package com.liftlife.liftlife;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Repository
public class DB {
    private final DatabaseReference ref;

    
    public DB() throws IOException {
        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("/liftlife-firebase-adminsdk-connection-string.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://liftlife-b89d1-default-rtdb.europe-west1.firebasedatabase.app")
                .build();

        FirebaseApp.initializeApp(options);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        ref = database.getReference("test");
    }

    @PostConstruct
    private void init(){
        ref.setValueAsync("Hello, I'm working properly!");
    }
}