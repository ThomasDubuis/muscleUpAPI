package com.ynov.muscleup.service;

import com.ynov.muscleup.model.Customer;
import com.ynov.muscleup.model.Gym;
import com.ynov.muscleup.model.ProgramSeance;
import com.ynov.muscleup.model.Seance;
import com.ynov.muscleup.model.customer_args.Role;
import com.ynov.muscleup.model.customer_args.Visibility;
import com.ynov.muscleup.repository.SeanceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SeanceServiceTest {

    @InjectMocks
    SeanceService seanceService;

    @Mock
    SeanceRepository seanceRepository;
    @Mock
    CustomerService customerService;

    private final Gym GYM = new Gym("1", "gymName", "gymGroupName", "gymCity", "gymDepartment", "gymRegion", "gymCountry");
    private final Customer USER = new Customer("1", "Joe", "LeJoe", "JoeLeJoe@gmail.com", "passwordHashed", Role.USER, Visibility.ALL);
    private final Date DATE = new GregorianCalendar(2014, Calendar.JANUARY, 11).getTime();

    private final ProgramSeance PROGRAMSEANCE = new ProgramSeance();
    private final Seance SEANCE = new Seance("1", DATE, USER, GYM, 13d, Collections.singletonList(PROGRAMSEANCE));

    @Test
    void getSeanceByIdButSeanceNotExist() {
        when(seanceRepository.findById("1")).thenReturn(Optional.empty());

        Seance result = seanceService.getSeanceById("1");

        assertNull(result);
    }

    @Test
    void getSeanceByIdButSeanceNotForThisCustomer() {
        when(seanceRepository.findById("1")).thenReturn(Optional.of(SEANCE));
        Customer user2 = new Customer();
        user2.setEmail("OtherEmail@gmail.com");
        when(customerService.getCurrentCustomer()).thenReturn(user2);

        Seance result = seanceService.getSeanceById("1");

        assertNull(result);
    }

    @Test
    void getSeanceByIdShouldReturnSeance() {
        when(seanceRepository.findById("1")).thenReturn(Optional.of(SEANCE));
        when(customerService.getCurrentCustomer()).thenReturn(USER);

        Seance result = seanceService.getSeanceById("1");

        assertEquals(USER, result.getCustomer());
        assertEquals(DATE, result.getDate());
    }
}