package com.liftlife.liftlife.database;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;

@Repository
public class FirestoreConnector {
    private static Logger log = LoggerFactory.getLogger(FirestoreConnector.class);
    private static FirestoreConnector firestoreConnectorInstance;

    private final Firestore database;


    public FirestoreConnector() throws IOException {
//        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("/liftlife-b89d1-firebase-adminsdk-1v4ox-c62b51cb58.json");
        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("/resources/local_database_access.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://liftlife-b89d1-default-rtdb.europe-west1.firebasedatabase.app")
                .build();

            FirebaseApp.initializeApp(options);

         database = FirestoreClient.getFirestore();

    }

    public static synchronized FirestoreConnector getInstance() {
        if(firestoreConnectorInstance == null) {
            try {
                firestoreConnectorInstance = new FirestoreConnector();
                log.info("Connected to Firestore Database");
            } catch (IOException e) {
                log.error("Unable to connect to Firestore Database ", e);
            }
        }

        return firestoreConnectorInstance;
    }

    public Firestore getDatabase() {
        return database;
    }
}
