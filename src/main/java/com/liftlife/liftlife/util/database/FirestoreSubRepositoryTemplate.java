package com.liftlife.liftlife.util.database;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.liftlife.liftlife.util.exception.DbAccessException;
import com.liftlife.liftlife.util.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Slf4j
public class FirestoreSubRepositoryTemplate<T extends FirestoreEntity> {
    private final CollectionReference mainCollectionReference;
    @Autowired
    protected FirestoreMapper firestoreMapper;
    private final Class<T> classType;

    public FirestoreSubRepositoryTemplate(Class<T> subClassType, String mainClassLowerCase) {
        this.classType = subClassType;
        this.mainCollectionReference = FirestoreClient.getFirestore()
                .collection(mainClassLowerCase);
    }

    public FirestoreSubRepositoryTemplate(CollectionReference mainCollectionReference, FirestoreMapper firestoreMapper, Class<T> classType) {
        this.mainCollectionReference = mainCollectionReference;
        this.firestoreMapper = firestoreMapper;
        this.classType = classType;
    }

    public FirestoreMapper getFirestoreMapper(){
        return this.firestoreMapper;
    }

    public String insert(T toSave, LinkedHashMap<String, String> documentIdsToSubCollections) {
        Map<String, Object> json = firestoreMapper.objectToMap(toSave);
        CollectionReference subReference = getSubCollectionReference(documentIdsToSubCollections);
        ApiFuture<DocumentReference> inserted = subReference.add(json);

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

    public T findById(String documentId,  LinkedHashMap<String, String> documentIdsToSubCollections) {
        CollectionReference subReference = getSubCollectionReference(documentIdsToSubCollections);
        DocumentReference documentReference = subReference.document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        
        try{
            DocumentSnapshot document = future.get();
            log.info("Result while saving to db: " + document);
            T entity = firestoreMapper.mapToObject(document, classType);
            entity.setDocumentId(documentId);
            return entity;
        } catch (ExecutionException e) {
            throw new DbAccessException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new DbAccessException(e);
        }
    }

    public <Q> List<T> findByField(String fieldName, Q fieldValue,  LinkedHashMap<String, String> documentIdsToSubCollections) {
        CollectionReference subReference = getSubCollectionReference(documentIdsToSubCollections);
        ApiFuture<QuerySnapshot> future = subReference.whereEqualTo(fieldName, fieldValue).get();
        return getResultFromQuery(future);
    }

    public <Q> List<T> findByFields(Map<String, Q> nameValueMap,  LinkedHashMap<String, String> documentIdsToSubCollections) {
        Query query = getSubCollectionReference(documentIdsToSubCollections);
        for (Map.Entry<String, Q> nameValue : nameValueMap.entrySet()) {
            query = query.whereEqualTo(nameValue.getKey(), nameValue.getValue());
        }
        ApiFuture<QuerySnapshot> future = query.get();
        return getResultFromQuery(future);
    }

    private List<T> getResultFromQuery(ApiFuture<QuerySnapshot> future) {
        List<T> list = new ArrayList<>();

        try {
            for(DocumentSnapshot documentSnapshot : future.get()){
                T entity = firestoreMapper.mapToObject(documentSnapshot, classType);
                entity.setDocumentId(documentSnapshot.getId());
                list.add(entity);
            }
            return list;
        } catch (ExecutionException e) {
            throw new DbAccessException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new DbAccessException(e);
        }
    }

    /*
    @param LinkedHashMap containing documentId to name of collection inside. Linked, because of maintaining order.
     */
    protected CollectionReference getSubCollectionReference(LinkedHashMap<String, String> documentIdsToSubCollections) {
        CollectionReference subReference = mainCollectionReference;
        for (Map.Entry<String, String> entry : documentIdsToSubCollections.entrySet()) {
            DocumentReference document = subReference.document(entry.getKey());
            try {
                if(!document.get().get().exists())
                    throw new NotFoundException("Document with id "+document.getId()+" does not exist in a tree.");
                subReference = document.collection(entry.getValue());
            } catch (ExecutionException e) {
                throw new DbAccessException(e);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new DbAccessException(e);
            }

        }
        return subReference;
    }

    public WriteResult update(T toChange, LinkedHashMap<String, String> documentIdsToSubCollections) {
        if (toChange.getDocumentId() == null)
            throw new IllegalArgumentException("DocumentId of updated element should not be null");
        CollectionReference subReference = getSubCollectionReference(documentIdsToSubCollections);
        DocumentReference documentReference = subReference.document(toChange.getDocumentId());
        try {
            if(!documentReference.get().get().exists())
                throw new NotFoundException("Object with id "+toChange.getDocumentId()+" does not exist");
            Map<String, Object> json = firestoreMapper.objectToMap(toChange);
            ApiFuture<WriteResult> result = documentReference.update(json);
            return result.get();
        } catch (ExecutionException e) {
            throw new DbAccessException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new DbAccessException(e);
        }
    }

    //TODO learn and add pagination
    public List<T> findAll(LinkedHashMap<String, String> documentIdsToSubCollections) {
        CollectionReference subReference = getSubCollectionReference(documentIdsToSubCollections);
        ApiFuture<QuerySnapshot> future = subReference.limit(100).get();
        return getResultFromQuery(future);
    }

    public void delete(String documentId, LinkedHashMap<String, String> documentIdsToSubCollections) {
        CollectionReference subReference = getSubCollectionReference(documentIdsToSubCollections);
        subReference.document(documentId).delete();
    }

    public void delete(T objectToDelete, LinkedHashMap<String, String> documentIdsToSubCollections) {
        CollectionReference subReference = getSubCollectionReference(documentIdsToSubCollections);
        subReference.document(objectToDelete.getDocumentId()).delete();
    }
}

