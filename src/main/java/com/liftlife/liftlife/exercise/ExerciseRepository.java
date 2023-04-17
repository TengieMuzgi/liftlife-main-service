package com.liftlife.liftlife.exercise;

import com.liftlife.liftlife.database.FirestoreMapper;
import com.liftlife.liftlife.database.FirestoreRepositoryTemplate;
import com.liftlife.liftlife.exercise.Exercise;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
@DependsOn({"firestoreConnector", "firestoreMapper"})
public class ExerciseRepository extends FirestoreRepositoryTemplate {

    public ExerciseRepository() {
        super(Exercise.class.getSimpleName().toLowerCase());
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
