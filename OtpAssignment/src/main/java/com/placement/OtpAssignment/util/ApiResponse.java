package com.placement.OtpAssignment.util;

import lombok.Data;

@Data
public class ApiResponse {
    private String message;

    public ApiResponse(String message) {
        this.message = message;
    }

    // getters and setters
}

