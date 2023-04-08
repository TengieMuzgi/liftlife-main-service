package com.liftlife.liftlife.database;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Slf4j
public class FirestoreRepositoryTemplate {

    private final CollectionReference collectionReference;
    private final FirestoreMapper firestoreMapper;

    public FirestoreRepositoryTemplate(String collectionName, FirestoreMapper firestoreMapper) {
        this.collectionReference = FirestoreClient.getFirestore()
                .collection(collectionName);
        this.firestoreMapper = firestoreMapper;
    }

    public <T extends FirestoreEntity> String insertTemplate(T toSave) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentReference> inserted = collectionReference.add(toSave);
        String insertedId = inserted.get().getId();
        log.info("Result while saving to db: " + insertedId);
        return insertedId;
    }

    public <T extends FirestoreEntity> T findOneByIdTemplate(String documentId, Class<T> tClass)
            throws ExecutionException, InterruptedException {
        DocumentReference documentReference = collectionReference.document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        log.info("Result while saving to db: " + document);
        return firestoreMapper.mapToObject(document, tClass);
    }

    //TODO findManyTemplate

    public <T extends FirestoreEntity> T updateTemplate(T toChange) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = collectionReference.document(toChange.getDocumentId());

        return null;
    }
}
