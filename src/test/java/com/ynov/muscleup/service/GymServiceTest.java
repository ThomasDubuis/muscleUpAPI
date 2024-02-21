package com.ynov.muscleup.service;

import com.ynov.muscleup.model.Customer;
import com.ynov.muscleup.model.Gym;
import com.ynov.muscleup.model.InscriptionGym;
import com.ynov.muscleup.model.customer_args.Role;
import com.ynov.muscleup.model.customer_args.Visibility;
import com.ynov.muscleup.model.utils.IdRequest;
import com.ynov.muscleup.repository.GymRepository;
import com.ynov.muscleup.repository.InscriptionGymRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ExtendWith(OutputCaptureExtension.class)
class GymServiceTest {

    @InjectMocks
    GymService gymService;

    @Mock
    GymRepository gymRepository;
    @Mock
    InscriptionGymRepository inscriptionGymRepository;
    @Mock
    CustomerService customerService;

    private final Gym GYM = new Gym("1", "gymName", "gymGroupName", "gymCity", "gymDepartment", "gymRegion", "gymCountry");
    private final Customer USER = new Customer("1", "Joe", "LeJoe", "JoeLeJoe@gmail.com", "passwordHashed", Role.USER, Visibility.ALL);
    private final Date DATE = new GregorianCalendar(2014, Calendar.JANUARY, 11).getTime();
    private final InscriptionGym INSCRIPTIONGYM = new InscriptionGym("1", USER, GYM, DATE);

    @Test
    void addGymButGymAlreadyExist(CapturedOutput output) {
        when(gymRepository.findByName("gymName")).thenReturn(Optional.of(GYM));

        Gym response = gymService.addGym(GYM);

        verify(gymRepository, times(0)).save(any());
        assertEquals(GYM, response);
        assertTrue(output.getOut().contains("Gym already exist : gymName"));
    }

    @Test
    void addGymShouldReturnGym() {
        when(gymRepository.findByName("gymName")).thenReturn(Optional.empty());
        when(gymRepository.save(GYM)).thenReturn(GYM);

        Gym response = gymService.addGym(GYM);

        verify(gymRepository, times(1)).save(any());
        assertEquals(GYM, response);
    }

    @Test
    void deleteGymButGymNotExist(CapturedOutput output) {
        IdRequest request = new IdRequest("1");
        when(gymRepository.findById("1")).thenReturn(Optional.empty());

        Gym response = gymService.deleteGym(request);

        assertNull(response);
        assertTrue(output.getOut().contains("Id does not exist in Gym"));
        verify(gymRepository, times(0)).delete(any());
    }

    @Test
    void deleteGymShouldDeleteGym() {
        IdRequest request = new IdRequest("1");
        when(gymRepository.findById("1")).thenReturn(Optional.of(GYM));

        Gym response = gymService.deleteGym(request);
        assertEquals(GYM, response);
        verify(gymRepository, times(1)).delete(any());
    }

    @Test
    void updateGymButGymNotExist(CapturedOutput output) {
        when(gymRepository.findById("1")).thenReturn(Optional.empty());

        Gym response = gymService.updateGym(GYM);

        verify(gymRepository, times(0)).save(any());
        assertNull(response);
        assertTrue(output.getOut().contains("Id does not exist in Gym"));
    }

    @Test
    void updateGymShouldUpdateGym() {
        when(gymRepository.findById("1")).thenReturn(Optional.of(GYM));
        when(gymRepository.save(GYM)).thenReturn(GYM);

        Gym response = gymService.updateGym(GYM);

        verify(gymRepository, times(1)).save(any());
        assertEquals(GYM, response);
    }

    @Test
    void getCategoriesShouldReturnAllCategories() {
        when(gymRepository.findAll()).thenReturn(Collections.singletonList(GYM));

        List<Gym> response = gymService.getGyms();

        assertEquals(GYM, response.get(0));
    }

    @Test
    void signUpToGymButGymIdNotExist() {
        IdRequest request = new IdRequest("1");
        when(gymRepository.findById("1")).thenReturn(Optional.empty());

        InscriptionGym result = gymService.signUpToGym(request);

        verify(inscriptionGymRepository, times(0)).save(any());
        assertNull(result);
    }

    @Test
    void signUpToGymShouldReturnInscriptionGym() {
        IdRequest request = new IdRequest("1");
        when(inscriptionGymRepository.save(any())).thenReturn(INSCRIPTIONGYM);
        when(gymRepository.findById("1")).thenReturn(Optional.of(GYM));
        when(customerService.getCurrentCustomer()).thenReturn(USER);

        InscriptionGym result = gymService.signUpToGym(request);

        verify(inscriptionGymRepository, times(1)).save(any());
        assertEquals("1", result.getId());
        assertEquals("1", result.getGym().getId());
        assertEquals("1", result.getCustomer().getId());
        assertEquals(DATE, result.getDate());
    }

    @Test
    void getGymIfCustomerIsRegisteredShouldReturnGym() {
        when(inscriptionGymRepository.findInscriptionGymsByCustomer(USER)).thenReturn(Collections.singletonList(INSCRIPTIONGYM));

        Gym result = gymService.getGymIfCustomerIsRegistered(USER, "1");

        assertEquals(GYM, result);
    }
}