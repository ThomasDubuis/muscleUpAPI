package com.ynov.muscleup.repository;

import com.ynov.muscleup.model.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GymRepository extends JpaRepository<Gym, String> {
    Optional<Gym> findByName(String name);
}
