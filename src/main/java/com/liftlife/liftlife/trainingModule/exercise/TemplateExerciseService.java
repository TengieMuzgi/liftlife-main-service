package com.liftlife.liftlife.trainingModule.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TemplateExerciseService {
    private final TemplateExerciseRepository repository;

    @Autowired
    public TemplateExerciseService(TemplateExerciseRepository repository) {
        this.repository = repository;
    }

    public String insertPresetExercise(Exercise newExercise) {
        return repository.insert(newExercise);
    }


    public Exercise findTemplateExercise(String documentId) {
        return repository.findById(documentId);
    }


    public List<Exercise> findPresetExercisesByBodyPart(String bodyPart) {
        List<Exercise> exercises = repository.findExerciseByBodyPart(bodyPart);
        if (exercises.isEmpty())
            exercises.add(new Exercise());
        return exercises;
    }

    public List<Exercise> findAll() {
        return repository.findAll();
    }

    public List<Exercise> findList(List<String> idList) {
        List<Exercise> exercises = new ArrayList<>();
        for (String id  : idList) {
            exercises.add(repository.findById(id));
        }
        return exercises;
    }
}
