package com.liftlife.liftlife.trainingModule.exercise;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainings/templateExercises")
public class TemplateExerciseController {
    private final TemplateExerciseService service;

    @Autowired
    public TemplateExerciseController(TemplateExerciseService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertPresetExercise(@RequestBody @Valid Exercise newExercise) {
        return ResponseEntity.ok(service.insertPresetExercise(newExercise));
    }

    @GetMapping("/find")
    public ResponseEntity<Exercise> findTemplateExercise(@RequestParam("id") String documentId) {
        return ResponseEntity.ok(service.findTemplateExercise(documentId));
    }

    @GetMapping("/findList")
    public ResponseEntity<List<Exercise>> findList(@RequestBody List<String> idList) {
        return ResponseEntity.ok(service.findList(idList));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Exercise>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/findBodyPart")
    public ResponseEntity<List<Exercise>> findPresetExercisesByBodyPart(@RequestParam("bodyPart") String bodyPart) {
        return ResponseEntity.ok(service.findPresetExercisesByBodyPart(bodyPart));
    }
}
