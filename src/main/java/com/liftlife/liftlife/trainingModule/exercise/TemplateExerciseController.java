package com.liftlife.liftlife.trainingModule.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainings/templateExercise")
public class TemplateExerciseController {
    private final TemplateExerciseService service;

    @Autowired
    public TemplateExerciseController(TemplateExerciseService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public String insertPresetExercise(@RequestBody Exercise newExercise) {
        return service.insertPresetExercise(newExercise);
    }

    @GetMapping("/find")
    public Exercise findPresetExercise(@RequestParam("id") String documentId) {
        return service.findPresetExercise(documentId);
    }

    @GetMapping("/findBodyPart")
    public List<Exercise> findPresetExercisesByBodyPart(@RequestParam("bodyPart") String bodyPart) {
        return service.findPresetExercisesByBodyPart(bodyPart);
    }
}
