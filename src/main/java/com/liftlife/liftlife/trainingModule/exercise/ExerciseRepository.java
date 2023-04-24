package com.liftlife.liftlife.trainingModule.exercise;

import com.liftlife.liftlife.util.database.FirestoreRepositoryTemplate;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@DependsOn({"firestoreConnector", "firestoreMapper"})
public class ExerciseRepository extends FirestoreRepositoryTemplate<Exercise> {

    public ExerciseRepository() {
        super(Exercise.class);
    }

    public List<Exercise> findExerciseByBodyPart(String bodyPart) {
        return super.findByField("bodyPart", bodyPart);
    }
}
