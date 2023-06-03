package com.liftlife.liftlife.dietModule.dietPlan;

import com.liftlife.liftlife.dietModule.dietDay.DietDay;
import com.liftlife.liftlife.dietModule.dietDay.FullDietDay;
import com.liftlife.liftlife.util.database.DietEntity;
import com.liftlife.liftlife.util.database.FirestoreEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FullDietPlan extends DietEntity {
    private String name;
    //id's for presentation, no implementation for now
    private String userId;
    private String trainerId;
    private List<FullDietDay> dietDays;
}