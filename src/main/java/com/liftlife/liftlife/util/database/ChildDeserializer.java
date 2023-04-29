package com.liftlife.liftlife.util.database;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.List;

public abstract class ChildDeserializer<T extends FirestoreEntity> extends JsonDeserializer<List<T>> {
    @Override
    public abstract List<T> deserialize(JsonParser jsonParser,
                               DeserializationContext deserializationContext)
            throws IOException, JacksonException;


}
