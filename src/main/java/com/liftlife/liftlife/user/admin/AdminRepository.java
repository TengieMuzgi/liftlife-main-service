package com.liftlife.liftlife.user.admin;

import com.google.cloud.firestore.DocumentReference;
import com.liftlife.liftlife.user.client.Client;
import com.liftlife.liftlife.user.coach.Coach;
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

    @Override
    public Admin findById(String id) {
        try {
            Admin admin = super.findById(id);
            if(admin != null)
                return admin;
        } catch (NullPointerException e) {
            throw new UserNotFoundException("User with authId: " + id + " is not registered");
        }
        throw new UserNotFoundException("User with authId: " + id + " is not registered");
    }

    public boolean isPresentById(String documentId) {
        try {
            Admin admin = super.findById(documentId);
            if(admin != null)
                return true;
        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }

}
