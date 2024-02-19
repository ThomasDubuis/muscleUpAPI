package com.ynov.muscleup.service;

import com.ynov.muscleup.model.Customer;
import com.ynov.muscleup.model.customer_args.Role;
import com.ynov.muscleup.model.customer_args.Visibility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class CustomerServiceTest {

    @InjectMocks
    CustomerService customerService;

    private final Customer USER = new Customer("1", "Joe", "LeJoe", "JoeLeJoe@gmail.com", "passwordHashed", Role.USER, Visibility.ALL);


    @Test
    void getCurrentCustomerShouldReturnCurrentCustomer() {
        SecurityContext context = new SecurityContext() {
            @Override
            public Authentication getAuthentication() {
                return new Authentication() {
                    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return null;}
                    @Override public Object getCredentials() { return null;}
                    @Override public Object getDetails() { return null;}
                    @Override public Object getPrincipal() { return USER;}
                    @Override public boolean isAuthenticated() { return false;}
                    @Override public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {}
                    @Override public String getName() { return null;}
                };
            }
            @Override public void setAuthentication(Authentication authentication) {}
        };
        try (MockedStatic<SecurityContextHolder> utilities = Mockito.mockStatic(SecurityContextHolder.class)) {
            utilities.when(SecurityContextHolder::getContext).thenReturn(context);
            Customer customer = customerService.getCurrentCustomer();
            assertEquals(USER, customer);
        }
    }

}