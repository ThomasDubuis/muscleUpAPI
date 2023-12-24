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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;

    public void updateIfNotNull(Category category) {
        if (category.name != null && !category.name.isBlank()) {
            this.name = category.name;
        }
        if (category.description != null && !category.description.isBlank()) {
            this.description = category.description;
        }
    }
}
