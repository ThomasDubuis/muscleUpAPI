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
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String groupName;
    private String city;
    private String department;
    private String region;
    private String country;

    public void updateIfNotNull(Gym gym) {
        if (gym.name != null && !gym.name.isBlank()) {
            this.name = gym.name;
        }
        if (gym.groupName != null && !gym.groupName.isBlank()) {
            this.groupName = gym.groupName;
        }
        if (gym.city != null && !gym.city.isBlank()) {
            this.city = gym.city;
        }
        if (gym.department != null && !gym.department.isBlank()) {
            this.department = gym.department;
        }
        if (gym.region != null && !gym.region.isBlank()) {
            this.region = gym.region;
        }
        if (gym.country != null && !gym.country.isBlank()) {
            this.country = gym.country;
        }
    }
}
