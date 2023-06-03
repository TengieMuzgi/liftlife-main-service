package com.liftlife.liftlife.user.client;

import com.liftlife.liftlife.common.UserRole;
import com.liftlife.liftlife.dietModule.dietPlan.DietPlan;
import com.liftlife.liftlife.trainingModule.trainingPlan.TrainingPlan;
import com.liftlife.liftlife.trainingModule.trainingSession.TrainingSession;
import com.liftlife.liftlife.user.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client extends User {
    private String coachId;
    private TrainingPlan trainingPlan;
    private DietPlan dietPlan;

    public Client(String authId, UserRole userRole, String coachId) {
        super(authId, userRole);
        this.coachId = coachId;
    }


}
