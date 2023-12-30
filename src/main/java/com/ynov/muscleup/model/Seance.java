package com.ynov.muscleup.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Date date;
    @ManyToOne @JoinColumn(name = "customerId", nullable = false) @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;
    @ManyToOne @JoinColumn(name = "GymId", nullable = false) @OnDelete(action = OnDeleteAction.CASCADE)
    private Gym gym;
    private Double score;
}
