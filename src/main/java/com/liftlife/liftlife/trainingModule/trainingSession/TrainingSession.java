package com.liftlife.liftlife.trainingModule.trainingSession;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.liftlife.liftlife.trainingModule.exercise.Exercise;
import com.liftlife.liftlife.trainingModule.exercise.serialization.ExerciseDeserializer;
import com.liftlife.liftlife.trainingModule.exercise.serialization.ExerciseSerializer;
import com.liftlife.liftlife.util.database.ChildSerializer;
import com.liftlife.liftlife.util.database.FirestoreEntity;
import lombok.*;

import java.util.List;

/*
POJO class representing one training session, that exists in client's/coach's calendar.
Is a part of training day.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrainingSession extends FirestoreEntity {
    private Integer duration;
    /* Commented till tests
    @JsonSerialize(using = ExerciseSerializer.class)
    @JsonDeserialize(using = ExerciseDeserializer.class)
    private List<Exercise> exercises;*/
    private String name;
    private boolean isTemplate;
    private String trainerId;
}
