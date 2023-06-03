package com.liftlife.liftlife.dietModule;

import com.liftlife.liftlife.dietModule.dietDay.DietDay;
import com.liftlife.liftlife.dietModule.dietDay.DietDayRepository;
import com.liftlife.liftlife.dietModule.dietDay.FullDietDay;
import com.liftlife.liftlife.dietModule.dietDay.meal.Meal;
import com.liftlife.liftlife.dietModule.dietPlan.DietPlan;
import com.liftlife.liftlife.dietModule.dietPlan.DietPlanRepository;
import com.liftlife.liftlife.dietModule.dietPlan.FullDietPlan;
import com.liftlife.liftlife.util.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DietServiceTest {

    @Mock
    private DietDayRepository dietDayRepository;

    @Mock
    private DietPlanRepository dietPlanRepository;

    @InjectMocks
    private DietService dietService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_ValidMealId_ReturnsMeal() {
        String dietId = "diet-123";
        String mealId = "meal-456";
        Meal expectedMeal = new Meal();
        when(dietDayRepository.findMealById(dietId, mealId)).thenReturn(expectedMeal);

        Meal result = dietService.findById(dietId, mealId);

        assertEquals(expectedMeal, result);
        verify(dietDayRepository).findMealById(dietId, mealId);
    }

    @Test
    void findById_InvalidMealId_ThrowsNotFoundException() {
        String dietId = "diet-123";
        String mealId = "meal-456";
        when(dietDayRepository.findMealById(dietId, mealId)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> dietService.findById(dietId, mealId));
        verify(dietDayRepository).findMealById(dietId, mealId);
    }

    @Test
    void delete_ValidObject_ReturnsOkResponse() {
        Meal meal = new Meal();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Meal deleted with ID "+meal.getDocumentId());

        ResponseEntity<String> result = dietService.delete("diet-123", meal);

        assertEquals(expectedResponse, result);
        verify(dietDayRepository).deleteMeal("diet-123", meal);
    }

    @Test
    void createMeal_ValidMeal_ReturnsResponseWithId() {
        String dietId = "diet-123";
        Meal meal = new Meal();
        String expectedResponse = "meal created";
        when(dietDayRepository.insertMeal(dietId, meal)).thenReturn(expectedResponse);

        ResponseEntity<String> result = dietService.createMeal(dietId, meal);

        assertEquals(ResponseEntity.ok(expectedResponse), result);
        verify(dietDayRepository).insertMeal(dietId, meal);
    }

    @Test
    void testFindByFields_NoMealsFound_ThrowsNotFoundException() {
        // Arrange
        String dietId = "diet123";
        Map<String, Object> fields = new HashMap<>();
        when(dietDayRepository.findMealByFields(dietId, fields)).thenReturn(new ArrayList<>());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> dietService.findByFields(dietId, fields));
    }

    @Test
    void test_ReturnFull(){
        DietDay dietDay = new DietDay(1,0,true,"3","1");
        when(dietDayRepository.findMeals("1")).thenReturn(new ArrayList<Meal>());

        assertEquals(dietService.returnFull(dietDay).getClass(),FullDietDay.class);
    }

    @Test
    void test_ConvertDietDay(){
        FullDietDay fullDietDay = new FullDietDay(1,0,true,"2",new ArrayList<Meal>());

        assertEquals(dietService.convertDietDay(fullDietDay).getClass(),DietDay.class);
    }
}