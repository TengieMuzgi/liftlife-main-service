package com.liftlife.liftlife.database;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.database.DatabaseReference;

import java.util.concurrent.ExecutionException;

public class FirestoreHelper {

    private DatabaseReference databaseReference;

    public FirestoreHelper(FirestoreConnector firestoreConnector) {
        this.databaseReference = FirestoreConnector.getInstance().getRef();
    }

    public <T> void saveToFirestore(String collectionName, T toSave) throws ExecutionException, InterruptedException {

    }
}
