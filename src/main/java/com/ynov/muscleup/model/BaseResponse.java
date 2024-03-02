package com.ynov.muscleup.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {
    private Boolean success;
    private T result;
    private String errorMessage;
    private Boolean displayError;



    public BaseResponse(T result) {
        this.success = true;
        this.result = result;
    }
    public BaseResponse(String errorMessage, boolean displayError) {
        this.success = false;
        this.displayError = displayError;
        this.errorMessage = errorMessage;
    }

    public static <T> ResponseEntity<BaseResponse<T>> ok(T result) {
        return ResponseEntity.ok(new BaseResponse<>(result));
    }

    public static <T> ResponseEntity<BaseResponse<T>> error(String errorMessage, boolean displayError) {
        return ResponseEntity.ok(new BaseResponse<>(errorMessage, displayError));
    }
    public static <T> ResponseEntity<BaseResponse<T>> error(String errorMessage) {
        return ResponseEntity.ok(new BaseResponse<>(errorMessage, true));
    }

}
