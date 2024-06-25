package com.ynov.muscleup.repository;

import com.ynov.muscleup.model.Customer;
import com.ynov.muscleup.model.Seance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeanceRepository extends JpaRepository<Seance, String> {
    List<Seance> findAllByCustomer(Customer customer);
}
