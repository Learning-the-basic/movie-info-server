package com.movieinfo.sharewatch.web.dto.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
public class AuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

}