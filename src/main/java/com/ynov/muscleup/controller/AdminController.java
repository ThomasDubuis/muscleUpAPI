package com.ynov.muscleup.controller;

import com.ynov.muscleup.model.Category;
import com.ynov.muscleup.model.Exercise;
import com.ynov.muscleup.model.utils.IdRequest;
import com.ynov.muscleup.service.CategoryService;
import com.ynov.muscleup.service.ExerciseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LogManager.getLogger(AdminController.class);

    @Autowired
    CategoryService categoryService;
    @Autowired
    ExerciseService exerciseService;

    @GetMapping("/helloadmin")
    public ResponseEntity<String> sayHelloOnlyAdmin() {
        return  ResponseEntity.ok("Hello from secured ADMIN endpoint");
    }

    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategory(@RequestBody Category request) {
        return  ResponseEntity.ok(categoryService.addCategory(request));
    }

    @DeleteMapping("/deleteCategory")
    public ResponseEntity<Category> deleteCategory(@RequestBody IdRequest id) {
        Category category = categoryService.deleteCategory(id);
        return category == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(category);
    }

    @PutMapping ("/updateCategory")
    public ResponseEntity<Category> updateCategory(@RequestBody Category request) {
        if (request.getId() == null || request.getId().isEmpty()) {
            logger.warn("Id not provided");
            return ResponseEntity.badRequest().build();
        }
        Category category = categoryService.updateCategory(request);
        return category == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(category);
    }



    @PostMapping("/addExercise")
    public ResponseEntity<Exercise> addExercise(@RequestBody Exercise request) {
        return  ResponseEntity.ok(exerciseService.addExercise(request));
    }

    @DeleteMapping("/deleteExercise")
    public ResponseEntity<Exercise> deleteExercise(@RequestBody IdRequest id) {
        Exercise exercise = exerciseService.deleteExercise(id);
        return exercise == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(exercise);
    }

    @PutMapping ("/updateExercise")
    public ResponseEntity<Exercise> updateExercise(@RequestBody Exercise request) {
        if (request.getId() == null || request.getId().isEmpty()) {
            logger.warn("Id not provided");
            return ResponseEntity.badRequest().build();
        }
        Exercise exercise = exerciseService.updateExercise(request);
        return exercise == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(exercise);
    }
}
