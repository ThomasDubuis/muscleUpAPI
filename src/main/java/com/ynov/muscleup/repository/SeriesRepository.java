package com.ynov.muscleup.repository;

import com.ynov.muscleup.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepository extends JpaRepository<Series, String> {
}
