package com.ynov.muscleup.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProgramSeance {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @JsonIgnore
    @ManyToOne @JoinColumn(name = "seanceId", nullable = false) @OnDelete(action = OnDeleteAction.CASCADE)
    private Seance seance;
    @ManyToOne @JoinColumn(name = "exerciseId", nullable = false) @OnDelete(action = OnDeleteAction.CASCADE)
    private Exercise exercise;
    @OneToMany(mappedBy = "programSeance", fetch = FetchType.LAZY)
    private List<Series> series;
}
