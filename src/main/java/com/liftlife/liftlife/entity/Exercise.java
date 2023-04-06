package com.liftlife.liftlife.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
Temporary exercise class, that resembles
collection from firestore
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Exercise {
    private String bodyPart;
    private String description;
    private int duration;
    private String name;
    private boolean isTemplate;
}
