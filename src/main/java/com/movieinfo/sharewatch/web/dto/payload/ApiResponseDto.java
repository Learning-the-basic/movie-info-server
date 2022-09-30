package com.movieinfo.sharewatch.web.dto.payload;

import lombok.Getter;

@Getter
public class ApiResponseDto {
    private boolean success;
    private String message;

    public ApiResponseDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}