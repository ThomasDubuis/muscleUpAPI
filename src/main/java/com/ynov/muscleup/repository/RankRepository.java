package com.ynov.muscleup.repository;

import com.ynov.muscleup.model.Rank;
import com.ynov.muscleup.model.rank_args.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankRepository extends JpaRepository<Rank, String> {

    List<Rank> findAllByCategory(Category category);
}
