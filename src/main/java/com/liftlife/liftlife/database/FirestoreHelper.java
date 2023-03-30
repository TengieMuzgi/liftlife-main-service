package com.liftlife.liftlife.database;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.database.DatabaseReference;

import java.util.concurrent.ExecutionException;

public class FirestoreHelper {

    private DocumentReference databaseReference;

    public FirestoreHelper(FirestoreConnector firestoreConnector) {
        this.databaseReference = firestoreConnector.getDatabase().collection("users").document("alovelace");

    }

    public <T> void saveToFirestore(String collectionName, T toSave) throws ExecutionException, InterruptedException {

    }
}
