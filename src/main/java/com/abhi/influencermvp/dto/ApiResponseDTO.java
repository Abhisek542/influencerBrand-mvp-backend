package com.abhi.influencermvp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponseDTO {
    private String message;
    private boolean success;
}

