package com.ynov.muscleup.repository;

import com.ynov.muscleup.model.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankRepository extends JpaRepository<Rank, String> {
}
