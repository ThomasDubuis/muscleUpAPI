package com.ynov.muscleup.controller;

import com.ynov.muscleup.model.Category;
import com.ynov.muscleup.model.utils.IdRequest;
import com.ynov.muscleup.service.CategoryService;
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
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }
        return  ResponseEntity.ok(categoryService.deleteCategory(id));
    }
    @PutMapping ("/updateCategory")
    public ResponseEntity<Category> updateCategory(@RequestBody Category request) {
        if (request.getId() == null || request.getId().isEmpty()) {
            logger.warn("Id not provided");
            return ResponseEntity.badRequest().build();
        }
        return  ResponseEntity.ok(categoryService.updateCategory(request));
    }
}
