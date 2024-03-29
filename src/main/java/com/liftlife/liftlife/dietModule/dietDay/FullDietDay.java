package com.liftlife.liftlife.dietModule.dietDay;

import com.liftlife.liftlife.dietModule.dietDay.meal.Meal;
import com.liftlife.liftlife.util.database.DietEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FullDietDay extends DietEntity {

    private String name;
    private int dayOfWeek;
    private transient int caloriesSum;
    private boolean isTemplate;
    private String trainerId;
    private List<Meal> meals;

}