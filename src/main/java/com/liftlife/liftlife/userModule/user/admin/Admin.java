package com.liftlife.liftlife.userModule.user.admin;

import com.liftlife.liftlife.common.UserRole;
import com.liftlife.liftlife.dietModule.dietPlan.DietPlan;
import com.liftlife.liftlife.trainingModule.trainingPlan.TrainingPlan;
import com.liftlife.liftlife.userModule.user.User;
import com.liftlife.liftlife.userModule.user.utils.RegistrationToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class Admin extends User {
    public Admin(String authId, UserRole userRole) {
        super(authId, userRole);
    }

    public RegistrationToken generateVerificationToken() {
        String token = UUID.randomUUID().toString();
        RegistrationToken registrationToken = new RegistrationToken(token, UserRole.ADMIN, this.getDocumentId());
        return registrationToken;
    }

}
