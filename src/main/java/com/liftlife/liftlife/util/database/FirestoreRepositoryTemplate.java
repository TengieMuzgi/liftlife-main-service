package com.liftlife.liftlife.util.database;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.liftlife.liftlife.util.exception.DbAccessException;
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

    public FirestoreRepositoryTemplate(Class<T> classType, CollectionReference collectionReference, FirestoreMapper mapper){
        this.classType = classType;
        this.collectionReference = collectionReference;
        this.firestoreMapper = mapper;
    }

    public FirestoreMapper getFirestoreMapper(){
        return this.firestoreMapper;
    }

    public String insert(T toSave) {
        Map<String, Object> json = firestoreMapper.objectToMap(toSave);
        ApiFuture<DocumentReference> inserted = collectionReference.add(json);

        try{
            String insertedId = inserted.get().getId();
            log.info("Saved to db with ID: " + insertedId);
            return insertedId;
        } catch (ExecutionException e) {
            throw new DbAccessException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new DbAccessException(e);
        }
    }

    public T findById(String documentId) {
        DocumentReference documentReference = collectionReference.document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        try{
            DocumentSnapshot document = future.get();
            log.info("Result while saving to db: " + document);
            return firestoreMapper.mapToObject(document, classType);
        } catch (ExecutionException e) {
            throw new DbAccessException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new DbAccessException(e);
        }
    }

    public <Q> List<T> findByField(String fieldName, Q fieldValue) {
        ApiFuture<QuerySnapshot> future = collectionReference.whereEqualTo(fieldName, fieldValue).get();
        return getResultFromQuery(future);
    }

    public <Q> List<T> findByFields(Map<String, Object> nameValueMap) {
        Query query = collectionReference;
        for (Map.Entry<String, Object> nameValue : nameValueMap.entrySet()) {
            query = query.whereEqualTo(nameValue.getKey(), nameValue.getValue());
        }
        ApiFuture<QuerySnapshot> future = query.get();
        return getResultFromQuery(future);
    }

    private List<T> getResultFromQuery(ApiFuture<QuerySnapshot> future) {
        List<T> list = new ArrayList<>();

        try {
            for(DocumentSnapshot documentSnapshot : future.get()){
                list.add(firestoreMapper.mapToObject(documentSnapshot, classType));
            }
            return list;
        } catch (ExecutionException e) {
            throw new DbAccessException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new DbAccessException(e);
        }
    }

    public WriteResult update(T toChange) {
        DocumentReference documentReference = collectionReference.document(toChange.getDocumentId());
        Map<String, Object> json = firestoreMapper.objectToMap(toChange);
        ApiFuture<WriteResult> result = documentReference.update(json);
        try {
            return result.get();
        } catch (ExecutionException e) {
            throw new DbAccessException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new DbAccessException(e);
        }
    }

    public void delete(String documentId) {
        collectionReference.document(documentId).delete();
    }

    public void delete(T objectToDelete) {
        collectionReference.document(objectToDelete.getDocumentId()).delete();
    }
}
