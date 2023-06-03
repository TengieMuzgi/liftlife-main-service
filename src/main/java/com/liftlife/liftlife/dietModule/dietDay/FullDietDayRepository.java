package com.liftlife.liftlife.dietModule.dietDay;

import com.liftlife.liftlife.util.database.FirestoreRepositoryTemplate;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

@Repository
@DependsOn("firestoreConnector")
public class FullDietDayRepository extends FirestoreRepositoryTemplate<FullDietDay> {

    public FullDietDayRepository() {
        super(FullDietDay.class);
    }
}
