package com.liftlife.liftlife.trainingModule.trainingSession.template;

import com.liftlife.liftlife.trainingModule.trainingSession.TrainingSession;
import com.liftlife.liftlife.util.database.FirestoreRepositoryTemplate;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@DependsOn("firestoreConnector")
public class TemplateTrainingSessionRepository extends FirestoreRepositoryTemplate<TrainingSession> {
    public TemplateTrainingSessionRepository() {
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
