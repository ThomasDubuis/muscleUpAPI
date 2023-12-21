package com.ynov.muscleup.repository;

import com.ynov.muscleup.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, String> {
    Optional<Exercise> findByName(String name);

}
