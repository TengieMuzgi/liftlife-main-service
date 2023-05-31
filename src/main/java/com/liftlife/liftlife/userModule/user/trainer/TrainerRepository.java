package com.liftlife.liftlife.userModule.user.trainer;

import com.liftlife.liftlife.util.database.FirestoreRepositoryTemplate;
import com.liftlife.liftlife.util.exception.UserNotFoundException;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@DependsOn("firestoreConnector")
public class TrainerRepository extends FirestoreRepositoryTemplate<Trainer> {
    public TrainerRepository() {
        super(Trainer.class);
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
