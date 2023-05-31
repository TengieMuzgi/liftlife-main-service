package com.liftlife.liftlife.user.trainer;

import com.google.cloud.firestore.DocumentReference;
import com.liftlife.liftlife.util.database.FirestoreRepositoryTemplate;
import com.liftlife.liftlife.util.exception.UserNotFoundException;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@DependsOn("firestoreConnector")
public class TrainerRepository extends FirestoreRepositoryTemplate<Trainer> {
    public TrainerRepository() {
        super(Trainer.class);
    }

    @Override
    public String insert(Trainer toSave) {
        Map<String, Object> json = firestoreMapper.objectToMap(toSave);
        DocumentReference inserted = collectionReference.document(toSave.getDocumentId());
        inserted.set(json);
        return toSave.getDocumentId();
    }

    public Trainer findByAuthId(String authId) {
        List<Trainer> trainers = super.findByField("authId", authId);
        if(trainers.isEmpty())
            throw new UserNotFoundException("User with authId: " + authId + " is not registered");
        else if(trainers.size() > 1)
            throw new UserNotFoundException("Multiple users with authId : " + authId + " was found");
        else return trainers.get(0);
    }

    public boolean isPresentByDocumentId(String documentId) {
        Trainer trainer = super.findById(documentId);
        if(trainer == null)
            return false;
        else return true;
    }

    public boolean isPresentByAuthId(String authId) {
        List<Trainer> trainers = super.findByField("authId", authId);
        if(trainers.isEmpty())
            return false;
        else return true;
    }

}
