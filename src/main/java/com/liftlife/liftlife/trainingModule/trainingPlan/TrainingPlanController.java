package com.liftlife.liftlife.trainingModule.trainingPlan;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("api/trainings/plans")
public class TrainingPlanController {
    private final TrainingPlanService trainingPlanService;

    @Autowired
    public TrainingPlanController(TrainingPlanService trainingPlanService) {
        this.trainingPlanService = trainingPlanService;
    }

    @GetMapping("/findByCoach")
    public ResponseEntity<List<TrainingPlan>> findPlansForCoach(@RequestParam String coachId) {
        return ResponseEntity.ok(trainingPlanService.findPlansForCoach(coachId));
    }

    @GetMapping("/findByClient")
    public ResponseEntity<List<TrainingPlan>> findPlansForClient(@RequestParam String clientId) {
        return ResponseEntity.ok(trainingPlanService.findPlansForClient(clientId));
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertPlan(@RequestBody @Valid TrainingPlan trainingPlan) {
        return ResponseEntity.ok(trainingPlanService.insertPlan(trainingPlan));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deletePlan(@RequestParam String planId) {
        trainingPlanService.deletePlan(planId);
        return ResponseEntity.ok().build();
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
