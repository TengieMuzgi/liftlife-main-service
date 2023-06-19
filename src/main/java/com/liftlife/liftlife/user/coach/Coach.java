package com.liftlife.liftlife.user.coach;

import com.liftlife.liftlife.common.CoachSpecialization;
import com.liftlife.liftlife.common.UserRole;
import com.liftlife.liftlife.dietModule.dietPlan.DietPlan;
import com.liftlife.liftlife.trainingModule.trainingPlan.TrainingPlan;
import com.liftlife.liftlife.user.user.User;
import com.liftlife.liftlife.user.utils.RegistrationToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Coach extends User {
    private CoachSpecialization specialization;
    private String description;
    private List<String> clientIdList;
    private List<TrainingPlan> trainingPlanList;
    private List<DietPlan> dietPlanList;

    public Coach(String authId, UserRole userRole) {
        super(authId, userRole);
        this.clientIdList = new ArrayList<>();
        this.trainingPlanList = new ArrayList<>();
        this.dietPlanList = new ArrayList<>();
        this.specialization = CoachSpecialization.PERSONAL;
        this.description = "";
    }

    public RegistrationToken generateVerificationToken() {
        String token = UUID.randomUUID().toString();
        RegistrationToken registrationToken = new RegistrationToken(token, UserRole.COACH, this.getDocumentId());
        return registrationToken;
    }

    public void addNewClient(String id) {
        clientIdList.add(id);
    }

}
