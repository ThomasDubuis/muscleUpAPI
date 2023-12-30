package com.ynov.muscleup.repository;

import com.ynov.muscleup.model.Customer;
import com.ynov.muscleup.model.InscriptionGym;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscriptionGymRepository extends JpaRepository<InscriptionGym, String> {

    List<InscriptionGym> findInscriptionGymsByCustomer(Customer customer);
}
