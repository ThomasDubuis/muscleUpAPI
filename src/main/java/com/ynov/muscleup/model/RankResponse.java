package com.ynov.muscleup.model;

import com.ynov.muscleup.model.rank_args.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RankResponse {
    private Category category;
    private Double score;
    private List<Rank> ranks;

}
