package com.liftlife.liftlife.database;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Slf4j
public class FirestoreRepositoryTemplate {

    private final CollectionReference collectionReference;

    public FirestoreRepositoryTemplate(String collectionName) {
        this.collectionReference = FirestoreClient.getFirestore()
                .collection(collectionName);
    }

    public <T> void saveToFirestore(T toSave) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = collectionReference.document();
        WriteResult result = documentReference.set(toSave).get();
        log.info("Result while saving to db: " + result);
    }
}
