package com.liftlife.liftlife.user.admin;

import com.liftlife.liftlife.common.UserRole;
import com.liftlife.liftlife.user.user.User;
import com.liftlife.liftlife.user.utils.RegistrationToken;
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
