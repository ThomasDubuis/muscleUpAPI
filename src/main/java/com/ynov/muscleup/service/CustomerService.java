package com.ynov.muscleup.service;

import com.ynov.muscleup.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    public Customer getCurrentCustomer() {
        Authentication authenticationToken = SecurityContextHolder.getContext().getAuthentication();
        return (Customer)authenticationToken.getPrincipal();
    }
}
