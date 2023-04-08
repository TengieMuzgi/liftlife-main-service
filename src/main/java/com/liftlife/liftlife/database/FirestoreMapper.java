package com.liftlife.liftlife.database;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FirestoreMapper {
    private final Gson gson;

    public FirestoreMapper() {
        this.gson = new GsonBuilder()
                .create();
    }

    public <T> T mapToObject(DocumentSnapshot documentSnapshot, Class<T> clazz) {
        Map<String, Object> data = documentSnapshot.getData();
        String json = gson.toJson(data);
        return gson.fromJson(json, clazz);
    }

    public Map<String, Object> mapToMap(QueryDocumentSnapshot documentSnapshot) {
        return documentSnapshot.getData();
    }

    public String mapToJson(Object object) {
        return gson.toJson(object);
    }
}
