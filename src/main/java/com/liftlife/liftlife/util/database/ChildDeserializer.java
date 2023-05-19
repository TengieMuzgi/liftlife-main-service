package com.liftlife.liftlife.util.database;

import com.google.gson.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

public class
ChildDeserializer<T extends FirestoreEntity> implements JsonDeserializer<AttributeList<T>> {
    private final Class<T> classType;

    public ChildDeserializer(Class<T> classType) {
        this.classType = classType;
    }

    @Override
    public AttributeList<T> deserialize(
            JsonElement jsonElement,
            Type type,
            JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        AttributeList<T> deserializedList = new AttributeList<>();
        JsonArray array = jsonElement.getAsJsonArray();
        for (JsonElement element : array) {
            try {
                T entity = classType.getDeclaredConstructor().newInstance();
                entity.setDocumentId(element.getAsString());
                deserializedList.add(entity);
            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                     IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return deserializedList;
    }
}
