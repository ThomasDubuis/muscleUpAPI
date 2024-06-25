package com.ynov.muscleup.controller;

import com.ynov.muscleup.model.BaseResponse;
import com.ynov.muscleup.model.Exercise;
import com.ynov.muscleup.model.Gym;
import com.ynov.muscleup.model.utils.IdRequest;
import com.ynov.muscleup.service.ExerciseService;
import com.ynov.muscleup.service.GymService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private static final Logger logger = LogManager.getLogger(AdminController.class);

    private static final String ID_NOT_PROVIDED = "Id not provided";

    @Autowired
    ExerciseService exerciseService;
    @Autowired
    GymService gymService;

    @GetMapping("/helloadmin")
    public ResponseEntity<BaseResponse<String>> sayHelloOnlyAdmin() {
        return  BaseResponse.ok("Hello from secured ADMIN endpoint");
    }

    @PostMapping("/addExercise")
    public ResponseEntity<BaseResponse<Exercise>> addExercise(@RequestBody Exercise request) {
        return  BaseResponse.ok(exerciseService.addExercise(request));
    }

    @DeleteMapping("/deleteExercise")
    public ResponseEntity<BaseResponse<Exercise>> deleteExercise(@RequestBody IdRequest id) {
        Exercise exercise = exerciseService.deleteExercise(id);
        return exercise == null ? BaseResponse.error("Id does not exist in Exercise") : BaseResponse.ok(exercise);
    }

    @PutMapping ("/updateExercise")
    public ResponseEntity<BaseResponse<Exercise>> updateExercise(@RequestBody Exercise request) {
        if (request.getId() == null || request.getId().isEmpty()) {
            logger.error(ID_NOT_PROVIDED);
            return BaseResponse.error(ID_NOT_PROVIDED);
        }
        Exercise exercise = exerciseService.updateExercise(request);
        return exercise == null ? BaseResponse.error("Id does not exist in Exercise") : BaseResponse.ok(exercise);
    }


    @PostMapping("/addGym")
    public ResponseEntity<BaseResponse<Gym>> addGym(@RequestBody Gym request) {
        return  BaseResponse.ok(gymService.addGym(request));
    }

    @DeleteMapping("/deleteGym")
    public ResponseEntity<BaseResponse<Gym>> deleteGym(@RequestBody IdRequest id) {
        Gym gym = gymService.deleteGym(id);
        return gym == null ? BaseResponse.error("Id does not exist in Gym") : BaseResponse.ok(gym);
    }

    @PutMapping ("/updateGym")
    public ResponseEntity<BaseResponse<Gym>> updateGym(@RequestBody Gym request) {
        if (request.getId() == null || request.getId().isEmpty()) {
            logger.error(ID_NOT_PROVIDED);
            return BaseResponse.error(ID_NOT_PROVIDED);
        }
        Gym gym = gymService.updateGym(request);
        return gym == null ? BaseResponse.error("Id does not exist in Gym"): BaseResponse.ok(gym);
    }
}
