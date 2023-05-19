package com.liftlife.liftlife.trainingModule.trainingPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/insert")
    public ResponseEntity<String> insertPlan(@RequestBody TrainingPlan trainingPlan) {
        return ResponseEntity.ok(trainingPlanService.insertPlan(trainingPlan));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deletePlan(@RequestParam String trainingPlanId) {
        trainingPlanService.deletePlan(trainingPlanId);
        return ResponseEntity.ok().build();
    }
}
