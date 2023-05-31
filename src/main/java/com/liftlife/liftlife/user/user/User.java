package com.liftlife.liftlife.user.user;

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
    private UserRole userRole;
    private Date registerDate;

    public User(String authId, UserRole userRole) {
        this.setDocumentId(authId);
        this.userRole = userRole;
        this.registerDate = new Date();
    }
}
