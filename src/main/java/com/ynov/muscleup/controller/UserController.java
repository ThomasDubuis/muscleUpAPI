package com.ynov.muscleup.controller;

import com.ynov.muscleup.model.Category;
import com.ynov.muscleup.model.Customer;
import com.ynov.muscleup.model.InscriptionGym;
import com.ynov.muscleup.model.Rank;
import com.ynov.muscleup.model.customer_args.Role;
import com.ynov.muscleup.model.customer_args.Visibility;
import com.ynov.muscleup.model.utils.IdRequest;
import com.ynov.muscleup.repository.CustomerRepository;
import com.ynov.muscleup.repository.RankRepository;
import com.ynov.muscleup.service.CategoryService;
import com.ynov.muscleup.service.CustomerService;
import com.ynov.muscleup.service.InscriptionGymService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    CategoryService categoryService;

    @Autowired
    CustomerService customerService;

    @Autowired
    InscriptionGymService inscriptionGymService;


    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {

        logger.info(customerService.getCurrentCustomer());
        return  ResponseEntity.ok("Hello from secured endpoint");
    }

    @PostMapping("/signUpToGym")
    public ResponseEntity<InscriptionGym> signUpToGym(IdRequest gymId) {
        if (gymId == null || gymId.getId() == null ||gymId.getId().isBlank()) {
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
