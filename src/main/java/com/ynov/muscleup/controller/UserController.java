package com.ynov.muscleup.controller;

import com.ynov.muscleup.model.*;
import com.ynov.muscleup.model.seance.SeanceRequest;
import com.ynov.muscleup.model.BaseResponse;
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
    SeanceService seanceService;



    @GetMapping("/hello")
    public ResponseEntity<BaseResponse<String>> sayHello() {

        logger.info(customerService.getCurrentCustomer());
        return BaseResponse.ok("Hello from secured endpoint");
    }

    @GetMapping("/getMe")
    public ResponseEntity<BaseResponse<Customer>> getMe() {
        return BaseResponse.ok(customerService.getCurrentCustomer());
    }

    @GetMapping("/getCategories")
    public ResponseEntity<BaseResponse<List<Category>>> getCategories() {
        return BaseResponse.ok(categoryService.getCategories());
    }

    @GetMapping("/getGyms")
    public ResponseEntity<BaseResponse<List<Gym>>> getGyms() {
        return BaseResponse.ok(gymService.getGyms());
    }

    @GetMapping("/getExercises")
    public ResponseEntity<BaseResponse<List<Exercise>>> getExercises() {
        return BaseResponse.ok(exerciseService.getExercises());
    }

    @PostMapping("/signUpToGym")
    public ResponseEntity<BaseResponse<InscriptionGym>> signUpToGym(@RequestBody IdRequest gymId) {
        if (gymId == null || gymId.getId() == null || gymId.getId().isBlank()) {
            logger.error("GymId is empty or null");
            return BaseResponse.error("GymId is empty or null");
        }
        InscriptionGym inscriptionGym = gymService.signUpToGym(gymId);
        if (inscriptionGym == null) {
            return BaseResponse.error("Id does not exist in Gym");
        }
        return BaseResponse.ok(inscriptionGym);
    }


    @PostMapping("/completeSeance")
    public ResponseEntity<BaseResponse<Seance>> postCompleteSeance(@RequestBody SeanceRequest seanceRequest) {
        if (!seanceRequest.isAllArgsFill()) {
            logger.error("All arg are not fill");
            return BaseResponse.error("All arg are not fill");
        }


        return seanceService.postCompleteSeance(seanceRequest);
    }

    @GetMapping("/getSeance/{seanceId}")
    public ResponseEntity<BaseResponse<Seance>> getSeanceById(@PathVariable String seanceId) {
        if (seanceId == null || seanceId.isBlank()) {
            logger.error("SeanceId is empty or null");
            return BaseResponse.error("SeanceId is empty or null");
        }
        Seance seance = seanceService.getSeanceById(seanceId);
        if (seance == null) {
            logger.error("Seance not exist or not for this customer");
            return BaseResponse.error("Seance not exist or not for this customer");
        }
        return BaseResponse.ok(seance);
    }
}
