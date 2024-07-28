package com.ynov.muscleup.model.trophy;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Trophy {
    private String name;
    private boolean reach;
    private String progress;
}
