package com.ynov.muscleup.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ynov.muscleup.model.rank_args.Category;
import com.ynov.muscleup.model.rank_args.Grade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private Grade grade;
    private Double minScore;
}
