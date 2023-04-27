package com.liftlife.liftlife.trainingModule.trainingSession;


import com.liftlife.liftlife.util.database.FirestoreEntity;
import lombok.*;

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
