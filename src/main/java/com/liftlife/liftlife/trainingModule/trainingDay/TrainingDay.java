package com.liftlife.liftlife.trainingModule.trainingDay;

import com.liftlife.liftlife.common.DayOfWeek;
import com.liftlife.liftlife.util.database.FirestoreEntity;
import lombok.*;

/*
POJO class representing one day in client's training plan,
which consists of zero or more training sets.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrainingDay extends FirestoreEntity {
    private DayOfWeek dayOfWeek;
}
