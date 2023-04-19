package com.liftlife.liftlife.training;

import com.liftlife.liftlife.exception.NotFoundException;
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

    public ResponseEntity<String> insert(Training training) {
        return ResponseEntity.ok().body("Training inserted with ID " + trainingRepository.insert(training));
    }

    public Training findByID(String id) {
        Training training = trainingRepository.findById(id);

        if(training == null)
            throw new NotFoundException("Training with ID " + id + " not found");

        return training;
    }

    public List<Training> findByTrainer(String trainerId) {
        List<Training> trainingList = trainingRepository.findTrainingByTrainer(trainerId);

        if(trainingList.isEmpty())
            throw new NotFoundException("Trainings for trainer with ID " + trainerId + " not found");

        return trainingList;
    }

    public List<Training> findByTemplate(boolean template) {
        List<Training> trainingList = trainingRepository.findTrainingByTemplate(template);

        if(trainingList.isEmpty())
            throw new NotFoundException("Trainings templates not found");

        return trainingList;
    }

    public List<Training> findByDate(String date) {
        List<Training> trainingList = trainingRepository.findByDate(date);

        if(trainingList.isEmpty())
            throw new NotFoundException("Trainings by date not found");

        return trainingList;
    }

}
