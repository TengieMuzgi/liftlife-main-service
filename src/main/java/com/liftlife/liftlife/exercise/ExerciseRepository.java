package com.liftlife.liftlife.exercise;

import com.liftlife.liftlife.database.FirestoreRepositoryTemplate;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
@DependsOn({"firestoreConnector", "firestoreMapper"})
public class ExerciseRepository extends FirestoreRepositoryTemplate<Exercise> {

    public ExerciseRepository() {
        super(Exercise.class);
    }

    public List<Exercise> findExerciseByBodyPart(String bodyPart) throws ExecutionException, InterruptedException {
        return super.findByField("bodyPart", bodyPart);
    }
}
