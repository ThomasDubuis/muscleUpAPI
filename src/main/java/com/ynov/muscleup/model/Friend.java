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
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne @JoinColumn(name = "customerId1", nullable = false) @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer1;
    @ManyToOne @JoinColumn(name = "customerId2", nullable = false) @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer2;
}
