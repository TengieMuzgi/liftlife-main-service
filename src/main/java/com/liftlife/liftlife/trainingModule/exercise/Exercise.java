package com.liftlife.liftlife.exercise;

import com.liftlife.liftlife.utils.database.FirestoreEntity;
import lombok.*;

/*
Temporary exercise class, that resembles
collection from firestore
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Exercise extends FirestoreEntity {
    private String bodyPart;
    private String description;
    private int duration;
    private String name;
    private boolean isTemplate;
}
