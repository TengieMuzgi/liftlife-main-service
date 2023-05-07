package com.liftlife.liftlife.trainingModule.exercise;

import com.liftlife.liftlife.util.database.FirestoreRepositoryTemplate;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
@DependsOn({"firestoreConnector", "firestoreMapper"})
public class TemplateExerciseRepository extends FirestoreRepositoryTemplate<Exercise> {

    public TemplateExerciseRepository() {
        super(Exercise.class);
    }

    public List<Exercise> findExerciseByBodyPart(String bodyPart) {
        return super.findByField("bodyPart", bodyPart);
    }

    public List<Exercise> findAllPresetExercises() throws ExecutionException, InterruptedException {
        return super.findAll();
    }
}
