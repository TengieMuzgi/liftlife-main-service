package com.liftlife.liftlife.trainingModule.trainingSession;

import com.liftlife.liftlife.util.database.FirestoreRepositoryTemplate;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@DependsOn("firestoreConnector")
public class TrainingSessionRepository extends FirestoreRepositoryTemplate<TrainingSession> {
    public TrainingSessionRepository() {
        super(TrainingSession.class);
    }

    public List<TrainingSession> findByDate(String date) {
        return super.findByField("date", date);
    }

    public List<TrainingSession> findTrainingByName(String name) {
        return super.findByField("name", name);
    }

    public List<TrainingSession> findTrainingByTemplate(boolean template) {
        return super.findByField("isTemplate", template);
    }

    public List<TrainingSession> findTrainingByTrainer(String trainerId) {
        return super.findByField("trainerId", trainerId);
    }


}
