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
public class ProgramSeance {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne @JoinColumn(name = "seanceId", nullable = false) @OnDelete(action = OnDeleteAction.CASCADE)
    private Seance seance;
    @ManyToOne @JoinColumn(name = "exerciceId", nullable = false) @OnDelete(action = OnDeleteAction.CASCADE)
    private Exercice exercice;
    private int numberOfRep;
    private double weight;
}
