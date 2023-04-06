package com.liftlife.liftlife.database;

import com.liftlife.liftlife.entity.Exercise;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;

@Repository
@DependsOn("firestoreConnector")
public class ExerciseRepository extends FirestoreRepositoryTemplate{
    public ExerciseRepository() {
        super("exercises");
    }

    public String insertExercise(Exercise exercise) throws ExecutionException, InterruptedException {
        return super.insertTemplate(exercise);
    }
}
