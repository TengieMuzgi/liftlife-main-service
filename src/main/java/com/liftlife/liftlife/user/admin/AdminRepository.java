package com.liftlife.liftlife.user.admin;

import com.google.cloud.firestore.DocumentReference;
import com.liftlife.liftlife.user.client.Client;
import com.liftlife.liftlife.util.database.FirestoreRepositoryTemplate;
import com.liftlife.liftlife.util.exception.UserNotFoundException;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@DependsOn("firestoreConnector")
public class AdminRepository extends FirestoreRepositoryTemplate<Admin> {
    public AdminRepository() {
        super(Admin.class);
    }

    @Override
    public String insert(Admin toSave) {
        Map<String, Object> json = firestoreMapper.objectToMap(toSave);
        DocumentReference inserted = collectionReference.document(toSave.getDocumentId());
        inserted.set(json);
        return toSave.getDocumentId();
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
