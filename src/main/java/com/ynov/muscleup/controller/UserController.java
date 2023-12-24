package com.ynov.muscleup.controller;

import com.ynov.muscleup.model.*;
import com.ynov.muscleup.model.utils.IdRequest;
import com.ynov.muscleup.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    CustomerService customerService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    GymService gymService;

    @Autowired
    ExerciseService exerciseService;

    @Autowired
    InscriptionGymService inscriptionGymService;



    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {

        logger.info(customerService.getCurrentCustomer());
        return  ResponseEntity.ok("Hello from secured endpoint");
    }

    @GetMapping("/getMe")
    public ResponseEntity<Customer> getMe() {
        return ResponseEntity.ok(customerService.getCurrentCustomer());
    }

    @GetMapping("/getCategories")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @GetMapping("/getGyms")
    public ResponseEntity<List<Gym>> getGyms() {
        return ResponseEntity.ok(gymService.getGyms());
    }

    @GetMapping("/getExercises")
    public ResponseEntity<List<Exercise>> getExercises() {
        return ResponseEntity.ok(exerciseService.getExercises());
    }

    @PostMapping("/signUpToGym")
    public ResponseEntity<InscriptionGym> signUpToGym(@RequestBody IdRequest gymId) {
        if (gymId == null || gymId.getId() == null || gymId.getId().isBlank()) {
            logger.error("GymId is empty or null");
            return ResponseEntity.badRequest().build();
        }
        InscriptionGym inscriptionGym = inscriptionGymService.signUpToGym(gymId);
        if (inscriptionGym == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(inscriptionGym);
    }
}
