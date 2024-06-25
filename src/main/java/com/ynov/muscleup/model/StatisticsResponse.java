package com.ynov.muscleup.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticsResponse {
    private Integer totalSeance;
    private Long averageSeanceTime;
    private Double averageSeanceWeight;
    private Double averageSeriesRep;
}