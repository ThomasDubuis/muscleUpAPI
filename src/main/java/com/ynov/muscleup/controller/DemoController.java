package com.ynov.muscleup.controller;

import com.ynov.muscleup.repository.CategoryRepository;
import com.ynov.muscleup.repository.CustomerRepository;
import com.ynov.muscleup.repository.RankRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo-controller")
public class DemoController {

    CategoryRepository categoryRepository;
    CustomerRepository customerRepository;
    RankRepository rankRepository;


    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {

        return  ResponseEntity.ok("Hello from secured endpoint");
    }
}
