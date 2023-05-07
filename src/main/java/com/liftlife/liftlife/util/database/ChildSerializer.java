package com.liftlife.liftlife.util.database;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class ChildSerializer<T extends FirestoreEntity> implements JsonSerializer<AttributeList<T>> {
    @Override
    public JsonElement serialize(AttributeList<T> ts, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray documentIds = new JsonArray();
        ts.stream()
                .map(T::getDocumentId).forEach(documentIds::add);
        return documentIds;
    }
}
