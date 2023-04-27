package com.liftlife.liftlife.trainingModule.trainingSession;

import com.liftlife.liftlife.trainingModule.exercise.Exercise;

import java.util.List;

public record TrainingSessionDTO (
       Integer duration,
       List<Exercise>exercises,
       String name,
       boolean isTemplate,
       String trainerId
) {}
