package com.liftlife.liftlife.exercise;

import com.liftlife.liftlife.database.FirestoreMapper;
import com.liftlife.liftlife.database.FirestoreRepositoryTemplate;
import com.liftlife.liftlife.exercise.Exercise;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
@DependsOn("firestoreConnector")
public class ExerciseRepository extends FirestoreRepositoryTemplate {
    public ExerciseRepository(FirestoreMapper firestoreMapper) {
        super("exercises", firestoreMapper);
    }

    public String insertExercise(Exercise exercise) throws ExecutionException, InterruptedException {
        return super.insertTemplate(exercise);
    }

    public Exercise findExerciseByDocumentId(String documentId) throws ExecutionException, InterruptedException {
        return super.findOneByIdTemplate(documentId, Exercise.class);
    }

    public List<Exercise> findExerciseByBodyPart(String bodyPart) throws ExecutionException, InterruptedException {
        return super.findByField("bodyPart",bodyPart, Exercise.class);
    }
}
