package com.liftlife.liftlife.userModule.user.utils;

import com.liftlife.liftlife.common.UserRole;
import com.liftlife.liftlife.util.database.FirestoreEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegistrationToken extends FirestoreEntity {
    private String token;
    private UserRole creationRole;
    private String creationId;
}
