package com.liftlife.liftlife.training;

import com.liftlife.liftlife.database.FirestoreMapper;
import com.liftlife.liftlife.database.FirestoreRepositoryTemplate;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
@DependsOn("firestoreConnector")
public class TrainingRepository extends FirestoreRepositoryTemplate<Training> {
    public TrainingRepository() {
        super(Training.class);
    }

    public List<Training> findByDate(String date) throws ExecutionException, InterruptedException {
        return super.findByField("date", date);
    }

    public List<Training> findTrainingByName(String name) throws ExecutionException, InterruptedException {
        return super.findByField("name", name);
    }

    public List<Training> findTrainingByTemplate(boolean template) throws ExecutionException, InterruptedException {
        return super.findByField("isTemplate", template);
    }

    public List<Training> findTrainingByTrainer(String trainerId) throws ExecutionException, InterruptedException {
        return super.findByField("trainerId", trainerId);
    }


}
