package com.liftlife.liftlife.trainingModule.exercise;
import com.liftlife.liftlife.trainingModule.exercise.Exercise;
import com.liftlife.liftlife.trainingModule.exercise.TemplateExerciseRepository;
import com.liftlife.liftlife.trainingModule.exercise.TemplateExerciseService;
import com.liftlife.liftlife.util.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TemplateExerciseServiceTest {
    @Mock
    private TemplateExerciseRepository exerciseRepository;

    private TemplateExerciseService exerciseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        exerciseService = new TemplateExerciseService(exerciseRepository);
    }

    @Test
    public void insertPresetExerciseTest() {
        Exercise exercise = new Exercise();
        exercise.setName("Squat");

        when(exerciseRepository.insert(exercise)).thenReturn("exerciseId");

        String result = exerciseService.insertPresetExercise(exercise);

        verify(exerciseRepository, times(1)).insert(exercise);
        verifyNoMoreInteractions(exerciseRepository);

        assert result.equals("exerciseId");
    }

    @Test
    public void findTemplateExerciseTest() {
        String documentId = "exerciseId";
        Exercise exercise = new Exercise();
        exercise.setName("Squat");

        when(exerciseRepository.findById(documentId)).thenReturn(exercise);

        Exercise result = exerciseService.findTemplateExercise(documentId);

        verify(exerciseRepository, times(1)).findById(documentId);
        verifyNoMoreInteractions(exerciseRepository);

        assert result == exercise;
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

        when(exerciseRepository.findExerciseByBodyPart(bodyPart)).thenReturn(exercises);

        List<Exercise> result = exerciseService.findPresetExercisesByBodyPart(bodyPart);

        verify(exerciseRepository, times(1)).findExerciseByBodyPart(bodyPart);
        verifyNoMoreInteractions(exerciseRepository);

        assert result == exercises;
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

        when(exerciseRepository.findAll()).thenReturn(exercises);

        List<Exercise> result = exerciseService.findAll();

        verify(exerciseRepository, times(1)).findAll();
        verifyNoMoreInteractions(exerciseRepository);

        assert result == exercises;
    }

    @Test
    public void findTemplateExerciseNotFoundTest() {
        String documentId = "nonExistentId";

        when(exerciseRepository.findById(documentId)).thenReturn(null);

        Exercise result = exerciseService.findTemplateExercise(documentId);

        verify(exerciseRepository, times(1)).findById(documentId);
        verifyNoMoreInteractions(exerciseRepository);

        assert result == null;
    }

    @Test
    public void findPresetExercisesByBodyPartNoResultsTest() {
        String bodyPart = "Arms";

        when(exerciseRepository.findExerciseByBodyPart(bodyPart)).thenReturn(Collections.emptyList());

        List<Exercise> result = exerciseService.findPresetExercisesByBodyPart(bodyPart);

        verify(exerciseRepository, times(1)).findExerciseByBodyPart(bodyPart);
        verifyNoMoreInteractions(exerciseRepository);

        assert result.isEmpty();
    }

    @Test
    public void findAllEmptyListTest() {
        List<Exercise> exercises = new ArrayList<>();

        when(exerciseRepository.findAll()).thenReturn(exercises);

        List<Exercise> result = exerciseService.findAll();

        verify(exerciseRepository, times(1)).findAll();
        verifyNoMoreInteractions(exerciseRepository);

        assert result.isEmpty();
    }
}
