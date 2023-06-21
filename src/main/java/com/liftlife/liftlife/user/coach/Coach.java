package com.liftlife.liftlife.user.coach;

import com.liftlife.liftlife.common.CoachSpecialization;
import com.liftlife.liftlife.common.UserRole;
import com.liftlife.liftlife.user.user.User;
import com.liftlife.liftlife.user.utils.RegistrationToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Coach extends User {
    private CoachSpecialization specialization;
    private String description;

    public Coach(String authId, UserRole userRole) {
        super(authId, userRole);
        this.specialization = CoachSpecialization.PERSONAL;
        this.description = "Description has not been set yet";
    }

    public RegistrationToken generateVerificationToken() {
        String token = UUID.randomUUID().toString();
        RegistrationToken registrationToken = new RegistrationToken(token, UserRole.COACH, this.getDocumentId());
        return registrationToken;
    }
    

}
