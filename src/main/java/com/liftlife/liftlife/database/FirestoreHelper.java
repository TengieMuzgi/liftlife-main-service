package com.liftlife.liftlife.database;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Repository
public class FirestoreHelper {

    private DocumentReference documentReference;


    public FirestoreHelper() {
        this.documentReference = FirestoreClient.getFirestore()
                .collection("users")
                .document("QsEcAzLNq2h6oH4AMdkO");

    }

    @PostConstruct
    public void sendSomething() throws ExecutionException, InterruptedException {
        Map<String, Object> data = new HashMap<>();
        data.put("first", "Ada");
        data.put("last", "Lovelace");
        data.put("born", 1815);
        //asynchronously write data
        ApiFuture<WriteResult> result = documentReference.set(data);
        System.out.println("Update time : " + result.get().getUpdateTime());
    }

    public <T> void saveToFirestore(String collectionName, T toSave) throws ExecutionException, InterruptedException {

    }
}
