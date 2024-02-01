package com.ynov.muscleup.controller;

import com.ynov.muscleup.model.Category;
import com.ynov.muscleup.model.Exercise;
import com.ynov.muscleup.model.Gym;
import com.ynov.muscleup.model.utils.IdRequest;
import com.ynov.muscleup.service.CategoryService;
import com.ynov.muscleup.service.ExerciseService;
import com.ynov.muscleup.service.GymService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class AdminControllerTest {
    @InjectMocks
    AdminController adminController;

    @Mock
    CategoryService categoryService;
    @Mock
    ExerciseService exerciseService;
    @Mock
    GymService gymService;

    @Test
    void addCategoryShouldReturnCategory() {
        Category category = new Category(null, "category", "descCategory");
        Mockito.when(categoryService.addCategory(category)).thenReturn(new Category("1", "category", "descCategory"));

        ResponseEntity<Category> response = adminController.addCategory(category);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("category", Objects.requireNonNull(response.getBody()).getName());
        assertEquals("descCategory", Objects.requireNonNull(response.getBody()).getDescription());
        assertEquals("1", Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    void deleteCategoryShouldReturnCategory() {
        IdRequest request = new IdRequest("1");
        Mockito.when(categoryService.deleteCategory(request)).thenReturn(new Category("1", "category", "descCategory"));

        ResponseEntity<Category> response = adminController.deleteCategory(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("category", Objects.requireNonNull(response.getBody()).getName());
        assertEquals("descCategory", Objects.requireNonNull(response.getBody()).getDescription());
        assertEquals("1", Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    void deleteCategoryThatDoesNotExistShouldReturnBadRequest() {
        IdRequest request = new IdRequest("1");
        Mockito.when(categoryService.deleteCategory(request)).thenReturn(null); //Return null if id not exist in DB

        ResponseEntity<Category> response = adminController.deleteCategory(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void updateCategoryShouldReturnCategory() {
        Category category = new Category("1", "category", "descCategory");
        Mockito.when(categoryService.updateCategory(category)).thenReturn(new Category("1", "category", "descCategory"));

        ResponseEntity<Category> response = adminController.updateCategory(category);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("category", Objects.requireNonNull(response.getBody()).getName());
        assertEquals("descCategory", Objects.requireNonNull(response.getBody()).getDescription());
        assertEquals("1", Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    void updateCategoryWithNotIdShouldReturnBadRequest() {
        Category category = new Category(null, "category", "descCategory");

        ResponseEntity<Category> response = adminController.updateCategory(category);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void updateCategoryThatDoesNotExistShouldReturnBadRequest() {
        Category category = new Category("1", "category", "descCategory");
        Mockito.when(categoryService.updateCategory(category)).thenReturn(null); //Return null if id not exist in DB

        ResponseEntity<Category> response = adminController.updateCategory(category);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void addExerciseShouldReturnExercise() {
        Exercise exercise = new Exercise(null, "Exercise", 1d, 1d, 1d, "descExercise");
        Mockito.when(exerciseService.addExercise(exercise)).thenReturn(new Exercise("1", "Exercise", 1d, 1d, 1d, "descExercise"));

        ResponseEntity<Exercise> response = adminController.addExercise(exercise);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Exercise", Objects.requireNonNull(response.getBody()).getName());
        assertEquals("descExercise", Objects.requireNonNull(response.getBody()).getDescription());
        assertEquals(1d, Objects.requireNonNull(response.getBody()).getOneRepScore());
        assertEquals(1d, Objects.requireNonNull(response.getBody()).getBasicWeight());
        assertEquals(1d, Objects.requireNonNull(response.getBody()).getWeightMultiplier());
        assertEquals("1", Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    void deleteExerciseShouldReturnExercise() {
        IdRequest request = new IdRequest("1");
        Mockito.when(exerciseService.deleteExercise(request)).thenReturn(new Exercise("1", "Exercise", 1d, 1d, 1d, "descExercise"));

        ResponseEntity<Exercise> response = adminController.deleteExercise(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Exercise", Objects.requireNonNull(response.getBody()).getName());
        assertEquals("descExercise", Objects.requireNonNull(response.getBody()).getDescription());
        assertEquals(1d, Objects.requireNonNull(response.getBody()).getOneRepScore());
        assertEquals(1d, Objects.requireNonNull(response.getBody()).getBasicWeight());
        assertEquals(1d, Objects.requireNonNull(response.getBody()).getWeightMultiplier());
        assertEquals("1", Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    void deleteExerciseThatDoesNotExistShouldReturnBadRequest() {
        IdRequest request = new IdRequest("1");
        Mockito.when(exerciseService.deleteExercise(request)).thenReturn(null); //Return null if id not exist in DB

        ResponseEntity<Exercise> response = adminController.deleteExercise(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void updateExerciseShouldReturnExercise() {
        Exercise exercise = new Exercise("1", "Exercise", 1d, 1d, 1d, "descExercise");
        Mockito.when(exerciseService.updateExercise(exercise)).thenReturn(new Exercise("1", "Exercise", 1d, 1d, 1d, "descExercise"));

        ResponseEntity<Exercise> response = adminController.updateExercise(exercise);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Exercise", Objects.requireNonNull(response.getBody()).getName());
        assertEquals("descExercise", Objects.requireNonNull(response.getBody()).getDescription());
        assertEquals(1d, Objects.requireNonNull(response.getBody()).getOneRepScore());
        assertEquals(1d, Objects.requireNonNull(response.getBody()).getBasicWeight());
        assertEquals(1d, Objects.requireNonNull(response.getBody()).getWeightMultiplier());
        assertEquals("1", Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    void updateExerciseWithNotIdShouldReturnBadRequest() {
        Exercise exercise = new Exercise(null, "Exercise", 1d, 1d, 1d, "descExercise");

        ResponseEntity<Exercise> response = adminController.updateExercise(exercise);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void updateExerciseThatDoesNotExistShouldReturnBadRequest() {
        Exercise exercise = new Exercise("1", "Exercise", 1d, 1d, 1d, "descExercise");
        Mockito.when(exerciseService.updateExercise(exercise)).thenReturn(null); //Return null if id not exist in DB

        ResponseEntity<Exercise> response = adminController.updateExercise(exercise);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void addGymShouldReturnGym() {
        Gym gym = new Gym(null, "gym", "group", "city", "department", "region", "country");
        Mockito.when(gymService.addGym(gym)).thenReturn(new Gym("1", "gym", "group", "city", "department", "region", "country"));

        ResponseEntity<Gym> response = adminController.addGym(gym);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("gym", Objects.requireNonNull(response.getBody()).getName());
        assertEquals("group", Objects.requireNonNull(response.getBody()).getGroupName());
        assertEquals("city", Objects.requireNonNull(response.getBody()).getCity());
        assertEquals("department", Objects.requireNonNull(response.getBody()).getDepartment());
        assertEquals("region", Objects.requireNonNull(response.getBody()).getRegion());
        assertEquals("country", Objects.requireNonNull(response.getBody()).getCountry());
        assertEquals("1", Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    void deleteGymShouldReturnGym() {
        IdRequest request = new IdRequest("1");
        Mockito.when(gymService.deleteGym(request)).thenReturn(new Gym("1", "gym", "group", "city", "department", "region", "country"));

        ResponseEntity<Gym> response = adminController.deleteGym(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("gym", Objects.requireNonNull(response.getBody()).getName());
        assertEquals("group", Objects.requireNonNull(response.getBody()).getGroupName());
        assertEquals("city", Objects.requireNonNull(response.getBody()).getCity());
        assertEquals("department", Objects.requireNonNull(response.getBody()).getDepartment());
        assertEquals("region", Objects.requireNonNull(response.getBody()).getRegion());
        assertEquals("country", Objects.requireNonNull(response.getBody()).getCountry());
        assertEquals("1", Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    void deleteGymThatDoesNotExistShouldReturnBadRequest() {
        IdRequest request = new IdRequest("1");
        Mockito.when(gymService.deleteGym(request)).thenReturn(null); //Return null if id not exist in DB

        ResponseEntity<Gym> response = adminController.deleteGym(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void updateGymShouldReturnGym() {
        Gym exercise = new Gym("1", "gym", "group", "city", "department", "region", "country");
        Mockito.when(gymService.updateGym(exercise)).thenReturn(new Gym("1", "gym", "group", "city", "department", "region", "country"));

        ResponseEntity<Gym> response = adminController.updateGym(exercise);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("gym", Objects.requireNonNull(response.getBody()).getName());
        assertEquals("group", Objects.requireNonNull(response.getBody()).getGroupName());
        assertEquals("city", Objects.requireNonNull(response.getBody()).getCity());
        assertEquals("department", Objects.requireNonNull(response.getBody()).getDepartment());
        assertEquals("region", Objects.requireNonNull(response.getBody()).getRegion());
        assertEquals("country", Objects.requireNonNull(response.getBody()).getCountry());
        assertEquals("1", Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    void updateGymWithNotIdShouldReturnBadRequest() {
        Gym gym = new Gym(null, "gym", "group", "city", "department", "region", "country");

        ResponseEntity<Gym> response = adminController.updateGym(gym);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void updateGymThatDoesNotExistShouldReturnBadRequest() {
        Gym gym = new Gym(null, "gym", "group", "city", "department", "region", "country");
        Mockito.when(gymService.updateGym(gym)).thenReturn(null); //Return null if id not exist in DB

        ResponseEntity<Gym> response = adminController.updateGym(gym);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}