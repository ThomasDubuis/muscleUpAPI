package com.ynov.muscleup.repository;

import com.ynov.muscleup.model.Exercice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciceRepository extends JpaRepository<Exercice, String> {
}
