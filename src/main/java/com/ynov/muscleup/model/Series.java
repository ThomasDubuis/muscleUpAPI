package com.ynov.muscleup.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @JsonIgnore
    @ManyToOne @JoinColumn(name = "programSeanceId", nullable = false) @OnDelete(action = OnDeleteAction.CASCADE)
    private ProgramSeance programSeance;
    private Integer numberOfRep;
    private Double weight;
}
