package com.liftlife.liftlife.trainingModule.trainingSession;

import com.liftlife.liftlife.util.database.FirestoreSubRepositoryTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TrainingSessionRepository extends FirestoreSubRepositoryTemplate<TrainingSession> {
    public TrainingSessionRepository() {
        super(TrainingSession.class, "trainingplan");
    }

}
