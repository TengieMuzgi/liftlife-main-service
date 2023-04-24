package com.liftlife.liftlife.util.database;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ChildSerializer<T extends FirestoreEntity> extends JsonSerializer<List<T>> {
    @Override
    public void serialize(List<T> tList,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException {
        List<String> tIds = tList.stream()
                .map(FirestoreEntity::getDocumentId)
                .collect(Collectors.toList());
        jsonGenerator.writeObject(tIds);
    }
}
