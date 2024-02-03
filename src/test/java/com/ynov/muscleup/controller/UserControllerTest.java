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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

        ResponseEntity<Customer> response = userController.getMe();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Joe", Objects.requireNonNull(response.getBody()).getFirstname());
        assertEquals("LeJoe", Objects.requireNonNull(response.getBody()).getLastname());
        assertEquals("JoeLeJoe@gmail.com", Objects.requireNonNull(response.getBody()).getEmail());
        assertNull(Objects.requireNonNull(response.getBody()).getHashedPassword());
        assertEquals(Role.USER, Objects.requireNonNull(response.getBody()).getRole());
        assertEquals(Visibility.ALL, Objects.requireNonNull(response.getBody()).getVisibility());
        assertEquals("1", Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    void getCategoriesShouldReturnCategories() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("1", null, null));
        categoryList.add(new Category("2", null, null));

        Mockito.when(categoryService.getCategories()).thenReturn(categoryList);

        ResponseEntity<List<Category>> response = userController.getCategories();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    void getGymsShouldReturnGyms() {
        List<Gym> gymList = new ArrayList<>();
        gymList.add(new Gym("1", null, null, null, null, null, null));
        gymList.add(new Gym("2", null, null, null, null, null, null));

        Mockito.when(gymService.getGyms()).thenReturn(gymList);

        ResponseEntity<List<Gym>> response = userController.getGyms();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    void getExercisesShouldReturnExercises() {
        List<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(new Exercise("1", null, null, null, null, null));
        exerciseList.add(new Exercise("2", null, null, null, null, null));

        Mockito.when(exerciseService.getExercises()).thenReturn(exerciseList);

        ResponseEntity<List<Exercise>> response = userController.getExercises();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    void signUpToGymShouldReturnGymInscription() {
        IdRequest request = new IdRequest("1");
        InscriptionGym inscriptionGym = new InscriptionGym("1", new Customer(), new Gym(), DATE);
        Mockito.when(gymService.signUpToGym(request)).thenReturn(inscriptionGym);

        ResponseEntity<InscriptionGym> response = userController.signUpToGym(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1", Objects.requireNonNull(response.getBody()).getId());
        assertEquals(DATE, Objects.requireNonNull(response.getBody()).getDate());
    }

    @Test
    void signUpToGymWithMissingIdShouldReturnBadRequest() {
        IdRequest request = new IdRequest();
        ResponseEntity<InscriptionGym> response = userController.signUpToGym(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void signUpToGymThatDoesNotExistShouldReturnBadRequest() {
        IdRequest request = new IdRequest("1");
        Mockito.when(gymService.signUpToGym(request)).thenReturn(null);

        ResponseEntity<InscriptionGym> response = userController.signUpToGym(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void postCompleteSeanceShouldReturnSeance() {
        SeanceRequest seanceRequest = new SeanceRequest("1", DATE, new ArrayList<>());
        seanceRequest.getProgramSeances().add(new SeanceRequest.ProgramSeanceRequest("1", 1, 1d));
        seanceRequest.getProgramSeances().add(new SeanceRequest.ProgramSeanceRequest("2", 2, 2d));

        Seance seance = new Seance("1", DATE, new Customer(), new Gym(), 1d, new ArrayList<>());

        Mockito.when(seanceService.postCompleteSeance(seanceRequest)).thenReturn(seance);

        ResponseEntity<Seance> response = userController.postCompleteSeance(seanceRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1", Objects.requireNonNull(response.getBody()).getId());
        assertEquals(DATE, Objects.requireNonNull(response.getBody()).getDate());
    }

    @Test
    void postCompleteSeanceWithMissingFieldShouldReturnBadRequest() {
        SeanceRequest seanceRequest = new SeanceRequest("1", null, new ArrayList<>());
        ResponseEntity<Seance> response = userController.postCompleteSeance(seanceRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void postCompleteSeanceWithGymIdDoesNotExistShouldReturnBadRequest() {
        SeanceRequest seanceRequest = new SeanceRequest("XXX", DATE, new ArrayList<>());
        seanceRequest.getProgramSeances().add(new SeanceRequest.ProgramSeanceRequest("1", 1, 1d));
        seanceRequest.getProgramSeances().add(new SeanceRequest.ProgramSeanceRequest("2", 2, 2d));

        Mockito.when(seanceService.postCompleteSeance(seanceRequest)).thenReturn(null);

        ResponseEntity<Seance> response = userController.postCompleteSeance(seanceRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getSeanceByIdShouldReturnSeance() {
        String seanceId = "1";
        Seance seance = new Seance("1", DATE, new Customer(), new Gym(), 1d, new ArrayList<>());

        Mockito.when(seanceService.getSeanceById(seanceId)).thenReturn(seance);

        ResponseEntity<Seance> response = userController.getSeanceById(seanceId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1", Objects.requireNonNull(response.getBody()).getId());
        assertEquals(DATE, Objects.requireNonNull(response.getBody()).getDate());
    }

    @Test
    void getSeanceByIdWithMissingIdShouldReturnBadRequest() {
        ResponseEntity<Seance> response = userController.getSeanceById(null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getSeanceByIdWithSeanceIdDoesNotExistShouldReturnSeance() {
        String seanceId = "1";
        Mockito.when(seanceService.getSeanceById(seanceId)).thenReturn(null);

        ResponseEntity<Seance> response = userController.getSeanceById(seanceId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}