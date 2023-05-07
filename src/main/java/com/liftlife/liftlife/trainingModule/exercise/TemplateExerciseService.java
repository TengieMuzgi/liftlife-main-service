package com.liftlife.liftlife.trainingModule.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class PresetExerciseService {
    private final PresetExerciseRepository repository;

    @Autowired
    public PresetExerciseService(PresetExerciseRepository repository) {
        this.repository = repository;
    }

    public String insertPresetExercise(Exercise newExercise) {
        return repository.insert(newExercise);
    }


    public Exercise findPresetExercise(String documentId) {
        return repository.findById(documentId);
    }


    public List<Exercise> findPresetExercisesByBodyPart(String bodyPart) {
        return repository.findExerciseByBodyPart(bodyPart);
    }

}
