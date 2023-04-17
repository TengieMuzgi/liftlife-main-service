package com.liftlife.liftlife.database;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/*

 */
@Slf4j
public class FirestoreRepositoryTemplate {
    private final CollectionReference collectionReference;

    @Autowired
    private FirestoreMapper firestoreMapper;

    public FirestoreRepositoryTemplate(String collectionName) {
        this.collectionReference = FirestoreClient.getFirestore()
                .collection(collectionName);
    }

    public <T extends FirestoreEntity> String insertTemplate(T toSave) throws ExecutionException, InterruptedException {
        Map<String, Object> json = firestoreMapper.objectToMap(toSave);
        ApiFuture<DocumentReference> inserted = collectionReference.add(json);
        String insertedId = inserted.get().getId();
        log.info("Result while saving to db: " + insertedId);
        return insertedId;
    }

    //TODO insertManyTemplate

    public <T extends FirestoreEntity> T findOneByIdTemplate(String documentId, Class<T> tClass)
            throws ExecutionException, InterruptedException {
        DocumentReference documentReference = collectionReference.document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        log.info("Result while saving to db: " + document);
        return firestoreMapper.mapToObject(document, tClass);
    }

    //TODO findManyTemplate

    public <T extends FirestoreEntity> WriteResult updateTemplate(T toChange) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = collectionReference.document(toChange.getDocumentId());
        Map<String, Object> json = firestoreMapper.objectToMap(toChange);
        ApiFuture<WriteResult> result = documentReference.update(json);
        return result.get();
    }

    public <T extends FirestoreEntity> void deleteTemplate(String documentId) {
        collectionReference.document(documentId).delete();
    }

    public <T extends FirestoreEntity> void deleteTemplate(T objectToDelete) {
        collectionReference.document(objectToDelete.getDocumentId()).delete();
    }
}
