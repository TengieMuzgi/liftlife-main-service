package com.liftlife.liftlife.user.client;

import com.google.cloud.firestore.DocumentReference;
import com.liftlife.liftlife.user.trainer.Trainer;
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

    public Client findByAuthId(String authId) {
        List<Client> clients = super.findByField("authId", authId);
        if(clients.isEmpty())
            throw new UserNotFoundException("User with authId: " + authId + " is not registered");
        else if(clients.size() > 1)
            throw new UserNotFoundException("Multiple users with authId : " + authId + " was found");
        else return clients.get(0);
    }

    public boolean isPresentByDocumentId(String documentId) {
        Client client = super.findById(documentId);
        if(client == null)
            return false;
        else return true;
    }

    public boolean isPresentByAuthId(String authId) {
        List<Client> clients = super.findByField("authId", authId);
        if(clients.isEmpty())
            return false;
        else return true;
    }
}
