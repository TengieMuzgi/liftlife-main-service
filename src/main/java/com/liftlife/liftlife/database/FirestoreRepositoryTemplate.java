package com.liftlife.liftlife.database;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/*

 */
@Slf4j
public class FirestoreRepositoryTemplate<T extends FirestoreEntity> {
    private final CollectionReference collectionReference;
    @Autowired
    private FirestoreMapper firestoreMapper;
    private Class<T> classType;

    public FirestoreRepositoryTemplate(Class<T> classType) {
        this.classType = classType;
        this.collectionReference = FirestoreClient.getFirestore()
                .collection(classType.getSimpleName().toLowerCase());
    }

    public FirestoreRepositoryTemplate(CollectionReference collectionReference, FirestoreMapper firestoreMapper){
        this.collectionReference = collectionReference;
        this.firestoreMapper = firestoreMapper;
    }

    public FirestoreMapper getFirestoreMapper(){
        return this.firestoreMapper;
    }

    public String insert(T toSave) throws ExecutionException, InterruptedException {
        Map<String, Object> json = firestoreMapper.objectToMap(toSave);
        ApiFuture<DocumentReference> inserted = collectionReference.add(json);
        String insertedId = inserted.get().getId();
        log.info("Result while saving to db: " + insertedId);
        return insertedId;
    }

    public T findById(String documentId)
            throws ExecutionException, InterruptedException {
        DocumentReference documentReference = collectionReference.document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        log.info("Result while saving to db: " + document);
        return firestoreMapper.mapToObject(document, classType);
    }

    //TODO - handle ExecutionException, InterruptedException in here

    public <Q> List<T> findByField(String fieldName, Q fieldValue) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = collectionReference.whereEqualTo(fieldName, fieldValue).get();
        List<T> list = new ArrayList<>();
        for(DocumentSnapshot documentSnapshot : future.get()){
            list.add(firestoreMapper.mapToObject(documentSnapshot, classType));
        }

        return list;
    }

    public WriteResult update(T toChange) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = collectionReference.document(toChange.getDocumentId());
        Map<String, Object> json = firestoreMapper.objectToMap(toChange);
        ApiFuture<WriteResult> result = documentReference.update(json);
        return result.get();
    }

    public void delete(String documentId) {
        collectionReference.document(documentId).delete();
    }

    public void delete(T objectToDelete) {
        collectionReference.document(objectToDelete.getDocumentId()).delete();
    }
}
