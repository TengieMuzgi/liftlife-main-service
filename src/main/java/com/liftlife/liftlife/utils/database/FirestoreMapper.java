package com.liftlife.liftlife.database;

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
    /*
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    class LocalDateSerializer implements JsonSerializer<LocalDate> {

        private DateTimeFormatter formatter;

        public LocalDateSerializer(DateTimeFormatter formatter) {
            this.formatter = formatter;
        }

        @Override
        public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(src));
        }
    }

    class LocalDateDeserializer implements JsonDeserializer<LocalDate>{

        private DateTimeFormatter formatter;

        public LocalDateDeserializer(DateTimeFormatter formatter) {
            this.formatter = formatter;
        }

        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            String dateStr = json.getAsJsonPrimitive().getAsString();
            return LocalDate.parse(dateStr, formatter);
        }
    }

     */

    public FirestoreMapper() {
        this.gson = new GsonBuilder()
                //.registerTypeAdapter(LocalDate.class, new LocalDateSerializer(formatter))
                //.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer(formatter))
                .create();

    }

    public <T> T mapToObject(DocumentSnapshot documentSnapshot, Class<T> clazz) {
        Map<String, Object> data = documentSnapshot.getData();
        String json = gson.toJson(data);
        return gson.fromJson(json, clazz);
    }

    //TODO god forbid this method, I'll find workaround related to firestore
    public Map<String, Object> objectToMap(Object obj) {
        String json = gson.toJson(obj);
        Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
        return gson.fromJson(json, typeOfMap);
    }

    public String mapToJson(Object object) {
        return gson.toJson(object);
    }
}
