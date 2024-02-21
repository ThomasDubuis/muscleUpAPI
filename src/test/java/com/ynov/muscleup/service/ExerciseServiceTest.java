package com.ynov.muscleup.service;

import com.ynov.muscleup.model.Exercise;
import com.ynov.muscleup.model.utils.IdRequest;
import com.ynov.muscleup.repository.ExerciseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ExtendWith(OutputCaptureExtension.class)
class ExerciseServiceTest {

    @InjectMocks
    ExerciseService exerciseService;
    
    @Mock
    ExerciseRepository exerciseRepository;
    
    private final Exercise EXERCISE = new Exercise("1", "ExerciseName", 10d, 10d, 0.3d, "Exercise Desc");

    @Test
    void addExerciseButExerciseAlreadyExist(CapturedOutput output) {
        when(exerciseRepository.findByName("ExerciseName")).thenReturn(Optional.of(EXERCISE));

        Exercise response = exerciseService.addExercise(EXERCISE);

        verify(exerciseRepository, times(0)).save(any());
        assertEquals(EXERCISE, response);
        assertTrue(output.getOut().contains("Exercise already exist : ExerciseName"));
    }

    @Test
    void addExerciseShouldReturnExercise() {
        when(exerciseRepository.findByName("ExerciseName")).thenReturn(Optional.empty());
        when(exerciseRepository.save(EXERCISE)).thenReturn(EXERCISE);

        Exercise response = exerciseService.addExercise(EXERCISE);

        verify(exerciseRepository, times(1)).save(any());
        assertEquals(EXERCISE, response);
    }

    @Test
    void deleteExerciseButExerciseNotExist(CapturedOutput output) {
        IdRequest request = new IdRequest("1");
        when(exerciseRepository.findById("1")).thenReturn(Optional.empty());

        Exercise response = exerciseService.deleteExercise(request);

        assertNull(response);
        assertTrue(output.getOut().contains("Id does not exist in Exercise"));
        verify(exerciseRepository, times(0)).delete(any());
    }

    @Test
    void deleteExerciseShouldDeleteExercise() {
        IdRequest request = new IdRequest("1");
        when(exerciseRepository.findById("1")).thenReturn(Optional.of(EXERCISE));

        Exercise response = exerciseService.deleteExercise(request);
        assertEquals(EXERCISE, response);
        verify(exerciseRepository, times(1)).delete(any());
    }

    @Test
    void updateExerciseButExerciseNotExist(CapturedOutput output) {
        when(exerciseRepository.findById("1")).thenReturn(Optional.empty());

        Exercise response = exerciseService.updateExercise(EXERCISE);

        verify(exerciseRepository, times(0)).save(any());
        assertNull(response);
        assertTrue(output.getOut().contains("Id does not exist in Exercise"));
    }

    @Test
    void updateExerciseShouldUpdateExercise() {
        when(exerciseRepository.findById("1")).thenReturn(Optional.of(EXERCISE));
        when(exerciseRepository.save(EXERCISE)).thenReturn(EXERCISE);

        Exercise response = exerciseService.updateExercise(EXERCISE);

        verify(exerciseRepository, times(1)).save(any());
        assertEquals(EXERCISE, response);
    }

    @Test
    void getCategoriesShouldReturnAllCategories() {
        when(exerciseRepository.findAll()).thenReturn(Collections.singletonList(EXERCISE));

        List<Exercise> response = exerciseService.getExercises();

        assertEquals(EXERCISE, response.get(0));
    }

    @Test
    void checkIfExerciseExistButExerciseNotExist() {
        when(exerciseRepository.findById("1")).thenReturn(Optional.empty());

        boolean result = exerciseService.checkIfExerciseExist("1");

        assertFalse(result);
    }

    @Test
    void checkIfExerciseExistShouldReturnTrue() {
        when(exerciseRepository.findById("1")).thenReturn(Optional.of(EXERCISE));

        boolean result = exerciseService.checkIfExerciseExist("1");

        assertTrue(result);
    }
}