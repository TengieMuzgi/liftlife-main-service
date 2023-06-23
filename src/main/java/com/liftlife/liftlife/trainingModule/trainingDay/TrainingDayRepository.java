package com.liftlife.liftlife.trainingModule.trainingDay;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.liftlife.liftlife.util.database.FirestoreSubRepositoryTemplate;
import com.liftlife.liftlife.util.exception.DbAccessException;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Repository
public class TrainingDayRepository extends FirestoreSubRepositoryTemplate<TrainingDay> {
    public TrainingDayRepository() {
        super(TrainingDay.class, "trainingplan");
    }

    @Override
    public String insert(TrainingDay toSave, LinkedHashMap<String, String> documentIdsToSubCollections) {
        Map<String, Object> json = firestoreMapper.objectToMap(toSave);
        CollectionReference subReference = super.getSubCollectionReference(documentIdsToSubCollections);
        DocumentReference inserted = subReference.document(toSave.getDocumentId());
        inserted.set(json);
        return toSave.getDocumentId();
    }
}
