package com.ynov.muscleup.controller;

import com.ynov.muscleup.model.*;
import com.ynov.muscleup.model.seance.SeanceRequest;
import com.ynov.muscleup.model.utils.IdRequest;
import com.ynov.muscleup.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    SeanceService seanceService;



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
        InscriptionGym inscriptionGym = gymService.signUpToGym(gymId);
        if (inscriptionGym == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(inscriptionGym);
    }


    @PostMapping("/completeSeance")
    public ResponseEntity<Seance> postCompleteSeance(@RequestBody SeanceRequest seanceRequest) {
        if (!seanceRequest.isAllArgsFill()) {
            logger.error("All arg are not fill");
            return ResponseEntity.badRequest().build();
        }

        Seance seance = seanceService.postCompleteSeance(seanceRequest);
        if (seance == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(seance);
    }

    @GetMapping("/getSeance/{seanceId}")
    public ResponseEntity<Seance> getSeanceById(@PathVariable String seanceId) {
        if (seanceId == null || seanceId.isBlank()) {
            logger.error("seanceId is empty or null");
            return ResponseEntity.badRequest().build();
        }
        Optional<Seance> seance = seanceService.getSeanceById(seanceId);
        if (seance.isEmpty()) {
            logger.warn("Seance not exist in database");
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(seance.get());
    }
}
