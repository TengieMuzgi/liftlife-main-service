package com.liftlife.liftlife.diet;


import com.liftlife.liftlife.database.FirestoreEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Diet extends FirestoreEntity {

    private String name;
    private String trainerId;
    private boolean isTemplate;
    //private List<Meal> meals;
}