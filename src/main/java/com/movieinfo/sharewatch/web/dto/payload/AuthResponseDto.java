package com.movieinfo.sharewatch.web.dto.payload;

import lombok.Getter;

@Getter
public class AuthResponseDto {
    private String accessToken;
    private String tokenType = "Bearer";

    public AuthResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

}