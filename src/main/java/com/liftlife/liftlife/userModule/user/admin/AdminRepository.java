package com.liftlife.liftlife.userModule.user.admin;

import com.liftlife.liftlife.util.database.FirestoreRepositoryTemplate;
import com.liftlife.liftlife.util.exception.UserNotFoundException;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@DependsOn("firestoreConnector")
public class AdminRepository extends FirestoreRepositoryTemplate<Admin> {
    public AdminRepository() {
        super(Admin.class);
    }

    public Admin findByAuthId(String authId) {
        List<Admin> admins = super.findByField("authId", authId);
        if(admins.isEmpty())
            throw new UserNotFoundException("User with authId: " + authId + " is not registered");
        else if(admins.size() > 1)
            throw new UserNotFoundException("Multiple users with authId : " + authId + " was found");
        else return admins.get(0);
    }

    public boolean isPresentByDocumentId(String documentId) {
        Admin admin = super.findById(documentId);
        if(admin == null)
            return false;
        else return true;
    }

    public boolean isPresentByAuthId(String authId) {
        List<Admin> admins = super.findByField("authId", authId);
        if(admins.isEmpty())
            return false;
        else return true;
    }
}
