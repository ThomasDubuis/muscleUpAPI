package com.ynov.muscleup.controller;

import com.ynov.muscleup.model.BaseResponse;
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
import static org.junit.jupiter.api.Assertions.assertFalse;

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

        ResponseEntity<BaseResponse<Category>> response = adminController.addCategory(category);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("category", Objects.requireNonNull(Objects.requireNonNull(response.getBody()).getResult()).getName());
        assertEquals("descCategory", Objects.requireNonNull(response.getBody().getResult()).getDescription());
        assertEquals("1", Objects.requireNonNull(response.getBody().getResult()).getId());
    }

    @Test
    void deleteCategoryShouldReturnCategory() {
        IdRequest request = new IdRequest("1");
        Mockito.when(categoryService.deleteCategory(request)).thenReturn(new Category("1", "category", "descCategory"));

        ResponseEntity<BaseResponse<Category>> response = adminController.deleteCategory(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("category", Objects.requireNonNull(Objects.requireNonNull(response.getBody()).getResult()).getName());
        assertEquals("descCategory", Objects.requireNonNull(response.getBody().getResult()).getDescription());
        assertEquals("1", Objects.requireNonNull(response.getBody().getResult()).getId());
    }

    @Test
    void deleteCategoryThatDoesNotExistShouldReturnBadRequest() {
        IdRequest request = new IdRequest("1");
        Mockito.when(categoryService.deleteCategory(request)).thenReturn(null); //Return null if id not exist in DB

        ResponseEntity<BaseResponse<Category>> response = adminController.deleteCategory(request);

        assertFalse(Objects.requireNonNull(response.getBody()).getSuccess());
        assertEquals("Id does not exist in Category", response.getBody().getErrorMessage());
    }

    @Test
    void updateCategoryShouldReturnCategory() {
        Category category = new Category("1", "category", "descCategory");
        Mockito.when(categoryService.updateCategory(category)).thenReturn(new Category("1", "category", "descCategory"));

        ResponseEntity<BaseResponse<Category>> response = adminController.updateCategory(category);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("category", Objects.requireNonNull(Objects.requireNonNull(response.getBody()).getResult()).getName());
        assertEquals("descCategory", Objects.requireNonNull(response.getBody().getResult()).getDescription());
        assertEquals("1", Objects.requireNonNull(response.getBody().getResult()).getId());
    }

    @Test
    void updateCategoryWithNotIdShouldReturnBadRequest() {
        Category category = new Category(null, "category", "descCategory");

        ResponseEntity<BaseResponse<Category>> response = adminController.updateCategory(category);

        assertFalse(Objects.requireNonNull(response.getBody()).getSuccess());
        assertEquals("Id not provided", response.getBody().getErrorMessage());
    }

    @Test
    void updateCategoryThatDoesNotExistShouldReturnBadRequest() {
        Category category = new Category("1", "category", "descCategory");
        Mockito.when(categoryService.updateCategory(category)).thenReturn(null); //Return null if id not exist in DB

        ResponseEntity<BaseResponse<Category>> response = adminController.updateCategory(category);

        assertFalse(Objects.requireNonNull(response.getBody()).getSuccess());
        assertEquals("Id does not exist in Category", response.getBody().getErrorMessage());
    }

    @Test
    void addExerciseShouldReturnExercise() {
        Exercise exercise = new Exercise(null, "Exercise", 1d, 1d, 1d, "descExercise");
        Mockito.when(exerciseService.addExercise(exercise)).thenReturn(new Exercise("1", "Exercise", 1d, 1d, 1d, "descExercise"));

        ResponseEntity<BaseResponse<Exercise>> response = adminController.addExercise(exercise);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Exercise", Objects.requireNonNull(Objects.requireNonNull(response.getBody()).getResult()).getName());
        assertEquals("descExercise", Objects.requireNonNull(response.getBody().getResult()).getDescription());
        assertEquals(1d, Objects.requireNonNull(response.getBody().getResult()).getOneRepScore());
        assertEquals(1d, Objects.requireNonNull(response.getBody().getResult()).getBasicWeight());
        assertEquals(1d, Objects.requireNonNull(response.getBody().getResult()).getWeightMultiplier());
        assertEquals("1", Objects.requireNonNull(response.getBody().getResult()).getId());
    }

    @Test
    void deleteExerciseShouldReturnExercise() {
        IdRequest request = new IdRequest("1");
        Mockito.when(exerciseService.deleteExercise(request)).thenReturn(new Exercise("1", "Exercise", 1d, 1d, 1d, "descExercise"));

        ResponseEntity<BaseResponse<Exercise>> response = adminController.deleteExercise(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Exercise", Objects.requireNonNull(Objects.requireNonNull(response.getBody()).getResult()).getName());
        assertEquals("descExercise", Objects.requireNonNull(response.getBody().getResult()).getDescription());
        assertEquals(1d, Objects.requireNonNull(response.getBody().getResult()).getOneRepScore());
        assertEquals(1d, Objects.requireNonNull(response.getBody().getResult()).getBasicWeight());
        assertEquals(1d, Objects.requireNonNull(response.getBody().getResult()).getWeightMultiplier());
        assertEquals("1", Objects.requireNonNull(response.getBody().getResult()).getId());
    }

    @Test
    void deleteExerciseThatDoesNotExistShouldReturnBadRequest() {
        IdRequest request = new IdRequest("1");
        Mockito.when(exerciseService.deleteExercise(request)).thenReturn(null); //Return null if id not exist in DB

        ResponseEntity<BaseResponse<Exercise>> response = adminController.deleteExercise(request);

        assertFalse(Objects.requireNonNull(response.getBody()).getSuccess());
        assertEquals("Id does not exist in Exercise", response.getBody().getErrorMessage());
    }

    @Test
    void updateExerciseShouldReturnExercise() {
        Exercise exercise = new Exercise("1", "Exercise", 1d, 1d, 1d, "descExercise");
        Mockito.when(exerciseService.updateExercise(exercise)).thenReturn(new Exercise("1", "Exercise", 1d, 1d, 1d, "descExercise"));

        ResponseEntity<BaseResponse<Exercise>> response = adminController.updateExercise(exercise);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Exercise", Objects.requireNonNull(Objects.requireNonNull(response.getBody()).getResult()).getName());
        assertEquals("descExercise", Objects.requireNonNull(response.getBody().getResult()).getDescription());
        assertEquals(1d, Objects.requireNonNull(response.getBody().getResult()).getOneRepScore());
        assertEquals(1d, Objects.requireNonNull(response.getBody().getResult()).getBasicWeight());
        assertEquals(1d, Objects.requireNonNull(response.getBody().getResult()).getWeightMultiplier());
        assertEquals("1", Objects.requireNonNull(response.getBody().getResult()).getId());
    }

    @Test
    void updateExerciseWithNotIdShouldReturnBadRequest() {
        Exercise exercise = new Exercise(null, "Exercise", 1d, 1d, 1d, "descExercise");

        ResponseEntity<BaseResponse<Exercise>> response = adminController.updateExercise(exercise);

        assertFalse(Objects.requireNonNull(response.getBody()).getSuccess());
        assertEquals("Id not provided", response.getBody().getErrorMessage());
    }

    @Test
    void updateExerciseThatDoesNotExistShouldReturnBadRequest() {
        Exercise exercise = new Exercise("1", "Exercise", 1d, 1d, 1d, "descExercise");
        Mockito.when(exerciseService.updateExercise(exercise)).thenReturn(null); //Return null if id not exist in DB

        ResponseEntity<BaseResponse<Exercise>> response = adminController.updateExercise(exercise);

        assertFalse(Objects.requireNonNull(response.getBody()).getSuccess());
        assertEquals("Id does not exist in Exercise", response.getBody().getErrorMessage());
    }

    @Test
    void addGymShouldReturnGym() {
        Gym gym = new Gym(null, "gym", "group", "city", "department", "region", "country");
        Mockito.when(gymService.addGym(gym)).thenReturn(new Gym("1", "gym", "group", "city", "department", "region", "country"));

        ResponseEntity<BaseResponse<Gym>> response = adminController.addGym(gym);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("gym", Objects.requireNonNull(Objects.requireNonNull(response.getBody()).getResult()).getName());
        assertEquals("group", Objects.requireNonNull(response.getBody().getResult()).getGroupName());
        assertEquals("city", Objects.requireNonNull(response.getBody().getResult()).getCity());
        assertEquals("department", Objects.requireNonNull(response.getBody().getResult()).getDepartment());
        assertEquals("region", Objects.requireNonNull(response.getBody().getResult()).getRegion());
        assertEquals("country", Objects.requireNonNull(response.getBody().getResult()).getCountry());
        assertEquals("1", Objects.requireNonNull(response.getBody().getResult()).getId());
    }

    @Test
    void deleteGymShouldReturnGym() {
        IdRequest request = new IdRequest("1");
        Mockito.when(gymService.deleteGym(request)).thenReturn(new Gym("1", "gym", "group", "city", "department", "region", "country"));

        ResponseEntity<BaseResponse<Gym>> response = adminController.deleteGym(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("gym", Objects.requireNonNull(Objects.requireNonNull(response.getBody()).getResult()).getName());
        assertEquals("group", Objects.requireNonNull(response.getBody().getResult()).getGroupName());
        assertEquals("city", Objects.requireNonNull(response.getBody().getResult()).getCity());
        assertEquals("department", Objects.requireNonNull(response.getBody().getResult()).getDepartment());
        assertEquals("region", Objects.requireNonNull(response.getBody().getResult()).getRegion());
        assertEquals("country", Objects.requireNonNull(response.getBody().getResult()).getCountry());
        assertEquals("1", Objects.requireNonNull(response.getBody().getResult()).getId());
    }

    @Test
    void deleteGymThatDoesNotExistShouldReturnBadRequest() {
        IdRequest request = new IdRequest("1");
        Mockito.when(gymService.deleteGym(request)).thenReturn(null); //Return null if id not exist in DB

        ResponseEntity<BaseResponse<Gym>> response = adminController.deleteGym(request);

        assertFalse(Objects.requireNonNull(response.getBody()).getSuccess());
        assertEquals("Id does not exist in Gym", response.getBody().getErrorMessage());
    }

    @Test
    void updateGymShouldReturnGym() {
        Gym exercise = new Gym("1", "gym", "group", "city", "department", "region", "country");
        Mockito.when(gymService.updateGym(exercise)).thenReturn(new Gym("1", "gym", "group", "city", "department", "region", "country"));

        ResponseEntity<BaseResponse<Gym>> response = adminController.updateGym(exercise);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("gym", Objects.requireNonNull(Objects.requireNonNull(response.getBody()).getResult()).getName());
        assertEquals("group", Objects.requireNonNull(response.getBody().getResult()).getGroupName());
        assertEquals("city", Objects.requireNonNull(response.getBody().getResult()).getCity());
        assertEquals("department", Objects.requireNonNull(response.getBody().getResult()).getDepartment());
        assertEquals("region", Objects.requireNonNull(response.getBody().getResult()).getRegion());
        assertEquals("country", Objects.requireNonNull(response.getBody().getResult()).getCountry());
        assertEquals("1", Objects.requireNonNull(response.getBody().getResult()).getId());
    }

    @Test
    void updateGymWithNotIdShouldReturnBadRequest() {
        Gym gym = new Gym(null, "gym", "group", "city", "department", "region", "country");

        ResponseEntity<BaseResponse<Gym>> response = adminController.updateGym(gym);

        assertFalse(Objects.requireNonNull(response.getBody()).getSuccess());
        assertEquals("Id not provided", response.getBody().getErrorMessage());
    }

    @Test
    void updateGymThatDoesNotExistShouldReturnBadRequest() {
        Gym gym = new Gym("1", "gym", "group", "city", "department", "region", "country");
        Mockito.when(gymService.updateGym(gym)).thenReturn(null); //Return null if id not exist in DB

        ResponseEntity<BaseResponse<Gym>> response = adminController.updateGym(gym);

        assertFalse(Objects.requireNonNull(response.getBody()).getSuccess());
        assertEquals("Id does not exist in Gym", response.getBody().getErrorMessage());
    }
}