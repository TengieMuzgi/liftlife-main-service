package com.liftlife.liftlife.trainingModule.trainingSession;

import com.liftlife.liftlife.trainingModule.trainingPlan.TrainingPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trainings/sessions/")
public class TrainingSessionController {
    private final TrainingPlanService trainingPlanService;

    @Autowired
    public TrainingSessionController(TrainingPlanService trainingPlanService) {
        this.trainingPlanService = trainingPlanService;
    }

    @PostMapping Mapping
    public ResponseEntity<String> insertTraining
}
