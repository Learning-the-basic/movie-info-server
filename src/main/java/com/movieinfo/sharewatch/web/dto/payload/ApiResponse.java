package com.movieinfo.sharewatch.web.dto.payload;

import lombok.Getter;

@Getter
public class ApiResponse {
    private boolean success;
    private String message;

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Getters and Setters (Omitted for brevity)
}