package com.liftlife.liftlife.dietModule.diet;


import com.liftlife.liftlife.utils.database.FirestoreEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DietDay extends FirestoreEntity {

    private String name;
    private String trainerId;
    private boolean isTemplate;
    //private List<Meal> meals;
}
