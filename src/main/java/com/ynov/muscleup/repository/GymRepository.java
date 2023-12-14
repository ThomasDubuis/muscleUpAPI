package com.ynov.muscleup.repository;

import com.ynov.muscleup.model.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym, String> {
}
