package com.liftlife.liftlife.user.client;

import com.google.cloud.firestore.DocumentReference;
import com.liftlife.liftlife.user.coach.Coach;
import com.liftlife.liftlife.util.database.FirestoreRepositoryTemplate;
import com.liftlife.liftlife.util.exception.UserNotFoundException;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@DependsOn("firestoreConnector")
public class ClientRepository extends FirestoreRepositoryTemplate<Client> {
    public ClientRepository() {
        super(Client.class);
    }

    @Override
    public String insert(Client toSave) {
        Map<String, Object> json = firestoreMapper.objectToMap(toSave);
        DocumentReference inserted = collectionReference.document(toSave.getDocumentId());
        inserted.set(json);
        return toSave.getDocumentId();
    }

    @Override
    public Client findById(String id) {
        try {
            Client client = super.findById(id);
            if(client != null)
                return client;
        } catch (NullPointerException e) {
            throw new UserNotFoundException("User with authId: " + id + " is not registered");
        }
        throw new UserNotFoundException("User with authId: " + id + " is not registered");
    }

    public boolean isPresentById(String documentId) {
        try {
            Client client = super.findById(documentId);
            if(client != null)
                return true;
        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }
}
