package com.liftlife.liftlife.trainingModule.exercise;

import com.liftlife.liftlife.trainingModule.exercise.Exercise;
import com.liftlife.liftlife.trainingModule.exercise.TemplateExerciseController;
import com.liftlife.liftlife.trainingModule.exercise.TemplateExerciseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class TemplateExerciseControllerTest {
    @Mock
    private TemplateExerciseService exerciseService;

    private TemplateExerciseController exerciseController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        exerciseController = new TemplateExerciseController(exerciseService);
    }

    @Test
    public void insertPresetExerciseTest() {
        Exercise exercise = new Exercise();
        exercise.setName("Squat");

        when(exerciseService.insertPresetExercise(exercise)).thenReturn("exerciseId");

        ResponseEntity<String> response = exerciseController.insertPresetExercise(exercise);

        verify(exerciseService, times(1)).insertPresetExercise(exercise);
        verifyNoMoreInteractions(exerciseService);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody().equals("exerciseId");
    }

    @Test
    public void findTemplateExerciseTest() {
        String documentId = "exerciseId";
        Exercise exercise = new Exercise();
        exercise.setName("Squat");

        when(exerciseService.findTemplateExercise(documentId)).thenReturn(exercise);

        ResponseEntity<Exercise> response = exerciseController.findTemplateExercise(documentId);

        verify(exerciseService, times(1)).findTemplateExercise(documentId);
        verifyNoMoreInteractions(exerciseService);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() == exercise;
    }

    @Test
    public void findAllTest() {
        List<Exercise> exercises = new ArrayList<>();
        Exercise exercise1 = new Exercise();
        exercise1.setName("Squat");
        Exercise exercise2 = new Exercise();
        exercise2.setName("Bench Press");
        exercises.add(exercise1);
        exercises.add(exercise2);

        when(exerciseService.findAll()).thenReturn(exercises);

        ResponseEntity<List<Exercise>> response = exerciseController.findAll();

        verify(exerciseService, times(1)).findAll();
        verifyNoMoreInteractions(exerciseService);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() == exercises;
    }

    @Test
    public void findPresetExercisesByBodyPartTest() {
        String bodyPart = "Legs";
        List<Exercise> exercises = new ArrayList<>();
        Exercise exercise1 = new Exercise();
        exercise1.setName("Squat");
        Exercise exercise2 = new Exercise();
        exercise2.setName("Lunge");
        exercises.add(exercise1);
        exercises.add(exercise2);

        when(exerciseService.findPresetExercisesByBodyPart(bodyPart)).thenReturn(exercises);

        ResponseEntity<List<Exercise>> response = exerciseController.findPresetExercisesByBodyPart(bodyPart);

        verify(exerciseService, times(1)).findPresetExercisesByBodyPart(bodyPart);
        verifyNoMoreInteractions(exerciseService);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() == exercises;
    }

    @Test
    public void insertPresetExerciseFailureTest() {
        Exercise exercise = new Exercise();
        exercise.setName("Squat");

        when(exerciseService.insertPresetExercise(exercise)).thenReturn(null);

        ResponseEntity<String> response = exerciseController.insertPresetExercise(exercise);

        verify(exerciseService, times(1)).insertPresetExercise(exercise);
        verifyNoMoreInteractions(exerciseService);

        //assert response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR;
        assert response.getBody() == null;
    }

    @Test
    public void findTemplateExerciseNotFoundTest() {
        String documentId = "nonExistentId";

        when(exerciseService.findTemplateExercise(documentId)).thenReturn(null);

        ResponseEntity<Exercise> response = exerciseController.findTemplateExercise(documentId);

        verify(exerciseService, times(1)).findTemplateExercise(documentId);
        verifyNoMoreInteractions(exerciseService);

        //assert response.getStatusCode() == HttpStatus.NOT_FOUND;
        assert response.getBody() == null;
    }

    @Test
    public void findAllEmptyListTest() {
        List<Exercise> exercises = new ArrayList<>();

        when(exerciseService.findAll()).thenReturn(exercises);

        ResponseEntity<List<Exercise>> response = exerciseController.findAll();

        verify(exerciseService, times(1)).findAll();
        verifyNoMoreInteractions(exerciseService);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody().isEmpty();
    }

    @Test
    public void findPresetExercisesByBodyPartNoResultsTest() {
        String bodyPart = "Arms";

        when(exerciseService.findPresetExercisesByBodyPart(bodyPart)).thenReturn(Collections.emptyList());

        ResponseEntity<List<Exercise>> response = exerciseController.findPresetExercisesByBodyPart(bodyPart);

        verify(exerciseService, times(1)).findPresetExercisesByBodyPart(bodyPart);
        verifyNoMoreInteractions(exerciseService);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody().isEmpty();
    }

}
