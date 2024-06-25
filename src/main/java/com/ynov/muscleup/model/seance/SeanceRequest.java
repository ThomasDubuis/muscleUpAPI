package com.ynov.muscleup.model.seance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeanceRequest {
    private String gymId;
    private Date startDate;
    private Date endDate;
    private List<ProgramSeanceRequest> programSeances;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProgramSeanceRequest {
        private String exerciseId;
        List<SeriesRequest> series;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SeriesRequest {
        private Integer numberOfRep;
        private Double weight;
    }


    public boolean isAllArgsFill() {
        if (gymId == null || gymId.isBlank()) {
            return false;
        }else if (programSeances == null || programSeances.isEmpty()){
            return false;
        }else {
            return true;
        }
    }
}
