package com.liftlife.liftlife.entity;

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
public class Exercise extends FirestoreEntity{
    private String bodyPart;
    private String description;
    private int duration;
    private String name;
    private boolean isTemplate;
}
