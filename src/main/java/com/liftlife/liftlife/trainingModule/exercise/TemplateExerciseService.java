package com.liftlife.liftlife.trainingModule.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return repository.findExerciseByBodyPart(bodyPart);
    }

    public List<Exercise> findAll() {
        return repository.findAll();
    }
}
