package com.liftlife.liftlife.userModule.user;

import com.liftlife.liftlife.common.UserRole;
import com.liftlife.liftlife.util.database.FirestoreEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User extends FirestoreEntity {
    private String authId;
    private UserRole userRole;
    private Date registerDate;

    public User(String authId, UserRole userRole) {
        this.authId = authId;
        this.userRole = userRole;
        this.registerDate = new Date();
    }
}
