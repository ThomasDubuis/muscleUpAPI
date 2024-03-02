package com.ynov.muscleup.controller;

import com.ynov.muscleup.model.*;
import com.ynov.muscleup.model.customer_args.Role;
import com.ynov.muscleup.model.customer_args.Visibility;
import com.ynov.muscleup.model.seance.SeanceRequest;
import com.ynov.muscleup.model.utils.IdRequest;
import com.ynov.muscleup.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserControllerTest {

    private final Date DATE = new GregorianCalendar(2014, Calendar.JANUARY, 11).getTime();

    @InjectMocks
    UserController userController;

    @Mock
    CustomerService customerService;
    @Mock
    CategoryService categoryService;
    @Mock
    GymService gymService;
    @Mock
    ExerciseService exerciseService;
    @Mock
    SeanceService seanceService;

    @Test
    void getMeShouldReturnMe() {
        Customer customer = new Customer("1", "Joe", "LeJoe", "JoeLeJoe@gmail.com", null, Role.USER, Visibility.ALL);
        Mockito.when(customerService.getCurrentCustomer()).thenReturn(customer);

        ResponseEntity<BaseResponse<Customer>> response = userController.getMe();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Customer result = Objects.requireNonNull(Objects.requireNonNull(response.getBody()).getResult());
        assertEquals("Joe", result.getFirstname());
        assertEquals("LeJoe", result.getLastname());
        assertEquals("JoeLeJoe@gmail.com", result.getEmail());
        assertNull(result.getHashedPassword());
        assertEquals(Role.USER, result.getRole());
        assertEquals(Visibility.ALL, result.getVisibility());
        assertEquals("1", result.getId());
    }

    @Test
    void getCategoriesShouldReturnCategories() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("1", null, null));
        categoryList.add(new Category("2", null, null));

        Mockito.when(categoryService.getCategories()).thenReturn(categoryList);

        ResponseEntity<BaseResponse<List<Category>>> response = userController.getCategories();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(Objects.requireNonNull(response.getBody()).getResult()).size());
    }

    @Test
    void getGymsShouldReturnGyms() {
        List<Gym> gymList = new ArrayList<>();
        gymList.add(new Gym("1", null, null, null, null, null, null));
        gymList.add(new Gym("2", null, null, null, null, null, null));

        Mockito.when(gymService.getGyms()).thenReturn(gymList);

        ResponseEntity<BaseResponse<List<Gym>>> response = userController.getGyms();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(Objects.requireNonNull(response.getBody()).getResult()).size());
    }

    @Test
    void getExercisesShouldReturnExercises() {
        List<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(new Exercise("1", null, null, null, null, null));
        exerciseList.add(new Exercise("2", null, null, null, null, null));

        Mockito.when(exerciseService.getExercises()).thenReturn(exerciseList);

        ResponseEntity<BaseResponse<List<Exercise>>> response = userController.getExercises();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(Objects.requireNonNull(response.getBody()).getResult()).size());
    }

    @Test
    void signUpToGymShouldReturnGymInscription() {
        IdRequest request = new IdRequest("1");
        InscriptionGym inscriptionGym = new InscriptionGym("1", new Customer(), new Gym(), DATE);
        Mockito.when(gymService.signUpToGym(request)).thenReturn(inscriptionGym);

        ResponseEntity<BaseResponse<InscriptionGym>> response = userController.signUpToGym(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1", Objects.requireNonNull(Objects.requireNonNull(response.getBody()).getResult()).getId());
        assertEquals(DATE, Objects.requireNonNull(response.getBody().getResult()).getDate());
    }

    @Test
    void signUpToGymWithMissingIdShouldReturnBadRequest() {
        IdRequest request = new IdRequest();
        ResponseEntity<BaseResponse<InscriptionGym>> response = userController.signUpToGym(request);

        assertFalse(Objects.requireNonNull(response.getBody()).getSuccess());
        assertEquals("GymId is empty or null", Objects.requireNonNull(response.getBody()).getErrorMessage());
    }

    @Test
    void signUpToGymThatDoesNotExistShouldReturnBadRequest() {
        IdRequest request = new IdRequest("1");
        Mockito.when(gymService.signUpToGym(request)).thenReturn(null);

        ResponseEntity<BaseResponse<InscriptionGym>> response = userController.signUpToGym(request);

        assertFalse(Objects.requireNonNull(response.getBody()).getSuccess());
        assertEquals("Id does not exist in Gym", Objects.requireNonNull(response.getBody()).getErrorMessage());
    }

    @Test
    void postCompleteSeanceShouldReturnSeance() {
        SeanceRequest seanceRequest = new SeanceRequest("1", DATE, new ArrayList<>());
        seanceRequest.getProgramSeances().add(new SeanceRequest.ProgramSeanceRequest("1", 1, 1d));
        seanceRequest.getProgramSeances().add(new SeanceRequest.ProgramSeanceRequest("2", 2, 2d));

        Seance seance = new Seance("1", DATE, new Customer(), new Gym(), 1d, new ArrayList<>());

        Mockito.when(seanceService.postCompleteSeance(seanceRequest)).thenReturn(BaseResponse.ok(seance));

        ResponseEntity<BaseResponse<Seance>> response = userController.postCompleteSeance(seanceRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1", Objects.requireNonNull(Objects.requireNonNull(response.getBody()).getResult()).getId());
        assertEquals(DATE, Objects.requireNonNull(response.getBody().getResult()).getDate());
    }

    @Test
    void postCompleteSeanceWithMissingFieldShouldReturnBadRequest() {
        SeanceRequest seanceRequest = new SeanceRequest("1", null, new ArrayList<>());
        ResponseEntity<BaseResponse<Seance>> response = userController.postCompleteSeance(seanceRequest);

        assertFalse(Objects.requireNonNull(response.getBody()).getSuccess());
        assertEquals("All arg are not fill", Objects.requireNonNull(response.getBody()).getErrorMessage());
    }

    @Test
    void postCompleteSeanceWithGymIdDoesNotExistShouldReturnBadRequest() {
        SeanceRequest seanceRequest = new SeanceRequest("XXX", DATE, new ArrayList<>());
        seanceRequest.getProgramSeances().add(new SeanceRequest.ProgramSeanceRequest("1", 1, 1d));
        seanceRequest.getProgramSeances().add(new SeanceRequest.ProgramSeanceRequest("2", 2, 2d));

        Mockito.when(seanceService.postCompleteSeance(seanceRequest)).thenReturn(BaseResponse.error("Gym id not exist in database or customer not register in this gym : " + seanceRequest.getGymId()));

        ResponseEntity<BaseResponse<Seance>> response = userController.postCompleteSeance(seanceRequest);

        assertFalse(Objects.requireNonNull(response.getBody()).getSuccess());
        assertEquals("Gym id not exist in database or customer not register in this gym : XXX", Objects.requireNonNull(response.getBody()).getErrorMessage());
    }

    @Test
    void getSeanceByIdShouldReturnSeance() {
        String seanceId = "1";
        Seance seance = new Seance("1", DATE, new Customer(), new Gym(), 1d, new ArrayList<>());

        Mockito.when(seanceService.getSeanceById(seanceId)).thenReturn(seance);

        ResponseEntity<BaseResponse<Seance>> response = userController.getSeanceById(seanceId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1", Objects.requireNonNull(Objects.requireNonNull(response.getBody()).getResult()).getId());
        assertEquals(DATE, Objects.requireNonNull(response.getBody().getResult()).getDate());
    }

    @Test
    void getSeanceByIdWithMissingIdShouldReturnBadRequest() {
        ResponseEntity<BaseResponse<Seance>> response = userController.getSeanceById(null);


        assertFalse(Objects.requireNonNull(response.getBody()).getSuccess());
        assertEquals("SeanceId is empty or null", Objects.requireNonNull(response.getBody()).getErrorMessage());
    }

    @Test
    void getSeanceByIdWithSeanceIdDoesNotExistShouldReturnSeance() {
        String seanceId = "1";
        Mockito.when(seanceService.getSeanceById(seanceId)).thenReturn(null);

        ResponseEntity<BaseResponse<Seance>> response = userController.getSeanceById(seanceId);

        assertFalse(Objects.requireNonNull(response.getBody()).getSuccess());
        assertEquals("Seance not exist or not for this customer", Objects.requireNonNull(response.getBody()).getErrorMessage());
    }
}