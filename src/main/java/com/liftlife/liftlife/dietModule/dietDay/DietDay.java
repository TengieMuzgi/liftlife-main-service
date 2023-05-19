package com.liftlife.liftlife.dietModule.dietDay;


import com.liftlife.liftlife.common.DayOfWeek;
import com.liftlife.liftlife.util.database.FirestoreEntity;
import lombok.*;

/*
POJO class representing one day in week in diet plan. Contains meals, that should be
eaten at each time of day.
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DietDay extends FirestoreEntity {
    private DayOfWeek dayOfWeek;
    private transient int caloriesSum;
    private boolean isTemplate;
    private String trainerId;

}
