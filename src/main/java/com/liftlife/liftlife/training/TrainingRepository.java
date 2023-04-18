package com.liftlife.liftlife.training;

import com.liftlife.liftlife.database.FirestoreRepositoryTemplate;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@DependsOn("firestoreConnector")
public class TrainingRepository extends FirestoreRepositoryTemplate<Training> {
    public TrainingRepository() {
        super(Training.class);
    }

    public List<Training> findByDate(String date) {
        return super.findByField("date", date);
    }

    public List<Training> findTrainingByName(String name) {
        return super.findByField("name", name);
    }

    public List<Training> findTrainingByTemplate(boolean template) {
        return super.findByField("isTemplate", template);
    }

    public List<Training> findTrainingByTrainer(String trainerId) {
        return super.findByField("trainerId", trainerId);
    }


}
