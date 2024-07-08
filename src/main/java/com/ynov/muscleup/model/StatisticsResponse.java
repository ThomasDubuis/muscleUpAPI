package com.ynov.muscleup.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ynov.muscleup.model.utils.DoubleSerializer;
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
    @JsonSerialize(using = DoubleSerializer.class)
    private Double averageSeanceWeight;
    @JsonSerialize(using = DoubleSerializer.class)
    private Double averageSeriesRep;
}