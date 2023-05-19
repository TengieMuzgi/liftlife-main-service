package com.liftlife.liftlife.util.database;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.liftlife.liftlife.trainingModule.exercise.Exercise;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Map;

@Component
public class FirestoreMapper {
    private final Gson gson;
    /*
    * TypeToken typeToken = new TypeToken<List<Exercise>>(){};
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(typeToken.getType(), new ChildSerializer<Exercise>())
                .registerTypeAdapter(typeToken.getType(), new ChildDeserializer<Exercise>(Exercise.class))
                * */
    public FirestoreMapper() {
        this.gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .registerTypeAdapter(new TypeToken<AttributeList<Exercise>>(){}.getType(),
                        new ChildSerializer<Exercise>())
                .registerTypeAdapter(new TypeToken<AttributeList<Exercise>>(){}.getType(),
                        new ChildDeserializer<>(Exercise.class))
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
