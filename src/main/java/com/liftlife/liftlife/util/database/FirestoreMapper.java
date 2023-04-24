package com.liftlife.liftlife.util.database;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
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

    public Map<String, Object> objectToMap(Object obj) {
        String json = gson.toJson(obj);
        Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
        return gson.fromJson(json, typeOfMap);
    }

    public String mapToJson(Object object) {
        return gson.toJson(object);
    }
}
