package com.liftlife.liftlife.dietModule.dietPlan;

import com.liftlife.liftlife.util.database.DietEntity;
import lombok.*;

import java.util.List;

/*
POJO class representing entire diet plan assigned to customer, contains all data about
what coach suggested to eat throughout the week. It cycles each week unless it's changed by coach.
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DietPlan extends DietEntity {
    private String name;
    //id's for presentation, no implementation for now
    private String userId;
    private String trainerId;
    private List<String> dietDays;
}
