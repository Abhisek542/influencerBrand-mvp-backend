package com.abhi.influencermvp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ApiErrorResponse {

    private int status;
    private String message;
    private String path;

    public ApiErrorResponse(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
    }

}
