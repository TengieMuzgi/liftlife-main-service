package com.liftlife.liftlife.trainingModule.exercise.serialization;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.liftlife.liftlife.trainingModule.exercise.Exercise;
import com.liftlife.liftlife.trainingModule.exercise.ExerciseRepository;
import com.liftlife.liftlife.util.database.ChildDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExerciseDeserializer extends ChildDeserializer<Exercise> {
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseDeserializer(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<Exercise> deserialize(JsonParser jsonParser,
                                      DeserializationContext deserializationContext)
            throws IOException, JacksonException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode root = codec.readTree(jsonParser);
        if (!root.isArray())
            throw new RuntimeException("Given json is not an array.");
        List<Exercise> exercises = new ArrayList<Exercise>();
        for (JsonNode node : root) {
            Exercise exercise = exerciseRepository.findById(root.asText());
            exercises.add(exercise);
        }
        return exercises;
    }
}
