package com.liftlife.liftlife.training;

import com.google.cloud.firestore.WriteResult;
import com.liftlife.liftlife.database.FirestoreEntity;
import com.liftlife.liftlife.database.FirestoreMapper;
import com.liftlife.liftlife.database.FirestoreRepositoryTemplate;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
@DependsOn("firestoreConnector")
public class TrainingRepository extends FirestoreRepositoryTemplate {
    public TrainingRepository(FirestoreMapper firestoreMapper) {
        super("trainings", firestoreMapper);
    }

    public String insertTraining(Training training) throws ExecutionException, InterruptedException {
        return super.insertTemplate(training);
    }

    public void deleteTraining(String trainingId){
        super.deleteTemplate(trainingId);
    }

    public void deleteTraining(Training training)
    {
        super.deleteTemplate(training);
    }

    public WriteResult updateTraining(Training training) throws ExecutionException, InterruptedException {
        return super.updateTemplate(training);
    }

    public Training findTrainingById(String trainingId) throws ExecutionException, InterruptedException {
        return super.findOneByIdTemplate(trainingId, Training.class);
    }

    public List<Training> findTrainingByDate(String date) throws ExecutionException, InterruptedException {
        return super.findByField("date",date, Training.class);
    }

    public List<Training> findTrainingByName(String name) throws ExecutionException, InterruptedException {
        return super.findByField("name",name, Training.class);
    }

    public List<Training> findTrainingByTemplate(boolean template) throws ExecutionException, InterruptedException {
        return super.findByField("template",template, Training.class);
    }

    public List<Training> findTrainingByTrainer(String trainerId) throws ExecutionException, InterruptedException {
        return super.findByField("trainerId",trainerId, Training.class);
    }


}
