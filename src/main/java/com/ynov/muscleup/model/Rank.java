package com.ynov.muscleup.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne @JoinColumn(name = "customerId", nullable = false) @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;
    @ManyToOne @JoinColumn(name = "categoryId", nullable = false) @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;
    private Double score;
}
