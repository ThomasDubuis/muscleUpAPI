package com.ynov.muscleup.repository;

import com.ynov.muscleup.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {


    Optional<Category> findByName(String name);
}
