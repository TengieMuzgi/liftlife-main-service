package com.liftlife.liftlife.trainingModule.training;

import com.liftlife.liftlife.utils.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {

    private TrainingRepository trainingRepository;

    @Autowired
    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public ResponseEntity<String> insert(TrainingSession trainingSession) {
        return ResponseEntity.ok().body("Training inserted with ID " + trainingRepository.insert(trainingSession));
    }

    public TrainingSession findByID(String id) {
        TrainingSession trainingSession = trainingRepository.findById(id);

        if(trainingSession == null)
            throw new NotFoundException("Training with ID " + id + " not found");

        return trainingSession;
    }

    public List<TrainingSession> findByTrainer(String trainerId) {
        List<TrainingSession> trainingSessionList = trainingRepository.findTrainingByTrainer(trainerId);

        if(trainingSessionList.isEmpty())
            throw new NotFoundException("Trainings for trainer with ID " + trainerId + " not found");

        return trainingSessionList;
    }

    public List<TrainingSession> findByTemplate(boolean template) {
        List<TrainingSession> trainingSessionList = trainingRepository.findTrainingByTemplate(template);

        if(trainingSessionList.isEmpty())
            throw new NotFoundException("Trainings templates not found");

        return trainingSessionList;
    }

    public List<TrainingSession> findByDate(String date) {
        List<TrainingSession> trainingSessionList = trainingRepository.findByDate(date);

        if(trainingSessionList.isEmpty())
            throw new NotFoundException("Trainings by date not found");

        return trainingSessionList;
    }

}
