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
public class InscriptionGym {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false) @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;
    @ManyToOne @JoinColumn(name = "gymId", nullable = false) @OnDelete(action = OnDeleteAction.CASCADE)
    private Gym gym;
    private Date date;
}
