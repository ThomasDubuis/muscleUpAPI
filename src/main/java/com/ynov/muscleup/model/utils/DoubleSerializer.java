package com.ynov.muscleup.model.utils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

public class DoubleSerializer extends JsonSerializer<Double> {

    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @Override
    public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(decimalFormat.format(value));
    }
}