package com.ynov.muscleup.model;

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
