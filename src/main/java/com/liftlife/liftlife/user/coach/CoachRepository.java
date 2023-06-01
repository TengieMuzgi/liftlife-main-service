package com.liftlife.liftlife.user.coach;

import com.google.cloud.firestore.DocumentReference;
import com.liftlife.liftlife.util.database.FirestoreRepositoryTemplate;
import com.liftlife.liftlife.util.exception.UserNotFoundException;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@DependsOn("firestoreConnector")
public class CoachRepository extends FirestoreRepositoryTemplate<Coach> {
    public CoachRepository() {
        super(Coach.class);
    }

    @Override
    public String insert(Coach toSave) {
        Map<String, Object> json = firestoreMapper.objectToMap(toSave);
        DocumentReference inserted = collectionReference.document(toSave.getDocumentId());
        inserted.set(json);
        return toSave.getDocumentId();
    }

    @Override
    public Coach findById(String id) {
        try {
            Coach coach = super.findById(id);
            if(coach != null)
                return coach;
        } catch (NullPointerException e) {
            throw new UserNotFoundException("User with authId: " + id + " is not registered");
        }
        throw new UserNotFoundException("User with authId: " + id + " is not registered");
    }

    public boolean isPresentById(String documentId) {
        try {
            Coach coach = super.findById(documentId);
            if(coach != null)
                return true;
        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }
}
