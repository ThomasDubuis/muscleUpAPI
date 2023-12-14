package com.ynov.muscleup.controller;

import com.ynov.muscleup.model.Category;
import com.ynov.muscleup.model.Customer;
import com.ynov.muscleup.model.Rank;
import com.ynov.muscleup.model.category_args.CategoryName;
import com.ynov.muscleup.model.customer_args.Role;
import com.ynov.muscleup.model.customer_args.Visibility;
import com.ynov.muscleup.repository.CategoryRepository;
import com.ynov.muscleup.repository.CustomerRepository;
import com.ynov.muscleup.repository.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo-controller")
public class DemoController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RankRepository rankRepository;


    @GetMapping
    public ResponseEntity<String> sayHello() {
        Customer customer = Customer.builder()
                .firstname("Thomas")
                .lastname("DUBUIS")
                .email("thomasdubuis13@gmail.com")
                .hashedPassword("$2a$10$vS0/FgOO1pMvhSX/koyXNeoOHw/JEuDD8y..ytHR5RqwcoMjhMWt2")
                .role(Role.USER)
                .visibility(Visibility.ALL)
                .build();

        customerRepository.save(customer);

        Category category = Category.builder()
                .name(CategoryName.PERFORMANCE)
                .description("Description de la categorie performance")
                .build();
        categoryRepository.save(category);

        Rank rank = Rank.builder()
                .category(category)
                .customer(customer)
                .score(999)
                .build();
        rankRepository.save(rank);


//        customerRepository.delete(customerRepository.findAll().get(0));
//        rankRepository.delete(rankRepository.findAll().get(0));



        return  ResponseEntity.ok("Hello from secured endpoint");
    }
}
