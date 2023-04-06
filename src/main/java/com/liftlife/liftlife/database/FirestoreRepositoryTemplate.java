package com.liftlife.liftlife.database;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.Gson;
import com.liftlife.liftlife.entity.FirestoreEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;

import java.util.concurrent.ExecutionException;

@DependsOn("firestoreConnector")
@Slf4j
public class FirestoreRepositoryTemplate {

    private final CollectionReference collectionReference;

    public FirestoreRepositoryTemplate(String collectionName) {
        this.collectionReference = FirestoreClient.getFirestore()
                .collection(collectionName);
    }

    public <T extends FirestoreEntity> String insertTemplate(T toSave) throws ExecutionException, InterruptedException {
        /*Gson gson = new Gson();
        gson.toJson(toSave);*/
        ApiFuture<DocumentReference> inserted = collectionReference.add(toSave);
        String insertedId = inserted.get().getId();
        log.info("Result while saving to db: " + insertedId);
        return insertedId;
    }

    public <T extends FirestoreEntity> T findOneTemplate(String documentId) throws ExecutionException, InterruptedException {
        return null;
    }

    public <T extends FirestoreEntity> T updateTemplate(T toChange) throws ExecutionException, InterruptedException {
        DocumentReference trainingRef = collectionReference.document(toChange.getDocumentId());
        return null;
    }
}
