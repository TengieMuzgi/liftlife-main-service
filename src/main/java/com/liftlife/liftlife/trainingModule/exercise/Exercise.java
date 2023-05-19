package com.liftlife.liftlife.trainingModule.exercise;

import com.liftlife.liftlife.util.database.FirestoreEntity;
import lombok.*;

/*
POJO class representing one exercise in training session assigned to client.
It's atomic part of training.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Exercise extends FirestoreEntity {
    private String name;
    private String bodyPart;
    private String description;
    private int numberOfReps;
    private int numberOfSets;
    private transient boolean isTemplate;
}
