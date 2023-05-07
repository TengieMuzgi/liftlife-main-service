package com.liftlife.liftlife.trainingModule.trainingSession;

import com.liftlife.liftlife.util.database.FirestoreRepositoryTemplate;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
@DependsOn("firestoreConnector")
public class TemplateTrainingSessionRepository extends FirestoreRepositoryTemplate<TrainingSession> {
    public TemplateTrainingSessionRepository() {
        super(TrainingSession.class);
    }

    public List<TrainingSession> findByDate(String date) {
        return super.findByField("date", date);
    }

    public List<TrainingSession> findByName(String name) {
        return super.findByField("name", name);
    }

    public List<TrainingSession> findByTemplate(boolean template) {
        return super.findByField("isTemplate", template);
    }

    public List<TrainingSession> findByTrainer(String trainerId) {
        return super.findByField("trainerId", trainerId);
    }

    @Override
    public List<TrainingSession> findAll() throws ExecutionException, InterruptedException {
        return super.findAll();
    }

}
