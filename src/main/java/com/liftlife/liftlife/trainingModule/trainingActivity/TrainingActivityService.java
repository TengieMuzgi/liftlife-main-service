package com.liftlife.liftlife.trainingModule.trainingActivity;

import com.liftlife.liftlife.util.exception.RetroActivityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class TrainingActivityService {
    private final TrainingActivityRepository trainingActivityRepository;

    @Autowired
    public TrainingActivityService(TrainingActivityRepository trainingActivityRepository) {
        this.trainingActivityRepository = trainingActivityRepository;
    }

    public List<TrainingActivity> findByMondayForClient(LocalDate monday, String clientId) {
        return trainingActivityRepository.findByMondayForClient(monday, clientId);
    }

    public String insertClientActivity(TrainingActivity trainingActivity) {
        if (!LocalDate.now().with(DayOfWeek.MONDAY).equals(trainingActivity.getWeeksMonday()))
            throw new RetroActivityException("Activities cannot be declared after week has ended.");
        trainingActivity.setTimeStamp(new Date());
        return trainingActivityRepository.insert(trainingActivity);
    }
}
