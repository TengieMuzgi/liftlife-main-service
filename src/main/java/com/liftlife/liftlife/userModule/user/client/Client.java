package com.liftlife.liftlife.userModule.user.client;

import com.liftlife.liftlife.common.UserRole;
import com.liftlife.liftlife.dietModule.dietDay.DietDay;
import com.liftlife.liftlife.dietModule.dietPlan.DietPlan;
import com.liftlife.liftlife.trainingModule.trainingPlan.TrainingPlan;
import com.liftlife.liftlife.trainingModule.trainingSession.TrainingSession;
import com.liftlife.liftlife.userModule.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client extends User {
    private String trainerId;
    private TrainingPlan trainingPlan;
    private DietPlan dietPlan;
    private List<TrainingSession> trainingSessionList;

    public Client(String authId, UserRole userRole, String trainerId) {
        super(authId, userRole);
        this.trainerId = trainerId;
    }
}
