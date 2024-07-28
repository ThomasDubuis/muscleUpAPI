package com.ynov.muscleup.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyExercise {
    private String id;
    private String name;
    private List<ExerciseHistory> history;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ExerciseHistory {
        private Double weight;
        private Date date;
    }
}
