package com.liftlife.liftlife.training;

import com.liftlife.liftlife.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class TrainingService {

    private TrainingRepository trainingRepository;

    @Autowired
    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    //TODO - handle ExecutionException, InterruptedException in repo template

    public ResponseEntity<String> insert(Training training) {
        try{
            return ResponseEntity.ok().body("Training inserted with ID " + trainingRepository.insert(training));
        } catch (ExecutionException | InterruptedException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    public Training findByID(String id) throws ExecutionException, InterruptedException {
        Training training = trainingRepository.findById(id);

        if(training == null)
            throw new NotFoundException("Training with ID " + id + " not found");

        return training;
    }

    public List<Training> findByTrainer(String trainerId) throws ExecutionException, InterruptedException {
        List<Training> trainingList = trainingRepository.findTrainingByTrainer(trainerId);

        if(trainingList.isEmpty())
            throw new NotFoundException("Trainings for trainer with ID " + trainerId + " not found");

        return trainingList;
    }

    public List<Training> findByTemplate(boolean template) throws ExecutionException, InterruptedException {
        List<Training> trainingList = trainingRepository.findTrainingByTemplate(template);

        if(trainingList.isEmpty())
            throw new NotFoundException("Trainings templates not found");

        return trainingList;
    }

    public List<Training> findByDate(String date) throws ExecutionException, InterruptedException {
        List<Training> trainingList = trainingRepository.findByDate(date);

        if(trainingList.isEmpty())
            throw new NotFoundException("Trainings by date not found");

        return trainingList;
    }

}
