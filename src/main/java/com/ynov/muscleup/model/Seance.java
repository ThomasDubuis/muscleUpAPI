package com.ynov.muscleup.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
    @OneToMany(mappedBy = "seance", fetch = FetchType.LAZY)
    private List<ProgramSeance> programSeances;
}
