package com.liftlife.liftlife.trainingModule.exercise;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
