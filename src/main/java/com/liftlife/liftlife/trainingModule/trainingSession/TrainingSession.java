package com.liftlife.liftlife.trainingModule.trainingSession;

import com.liftlife.liftlife.trainingModule.exercise.Exercise;
import com.liftlife.liftlife.util.database.AttributeList;
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

    private int startHour;
    private int finishHour;
    private String name;
    private AttributeList<Exercise> exercises;
    private boolean isTemplate;
    private String coachId;
}
