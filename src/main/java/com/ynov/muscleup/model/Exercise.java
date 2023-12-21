package com.ynov.muscleup.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private Double oneRepScore;
    private Double basicWeight;
    private Double weightMultiplier;
    private String description;

    public void updateIfNotNull(Exercise exercise) {
        if (exercise.name != null && !exercise.name.isBlank()) {
            this.name = exercise.name;
        }
        if (exercise.description != null && !exercise.description.isBlank()) {
            this.description = exercise.description;
        }
        if (exercise.oneRepScore != null) {
            this.oneRepScore = exercise.oneRepScore;
        }
        if (exercise.basicWeight != null) {
            this.basicWeight = exercise.basicWeight;
        }
        if (exercise.weightMultiplier != null) {
            this.weightMultiplier = exercise.weightMultiplier;
        }
    }
}
