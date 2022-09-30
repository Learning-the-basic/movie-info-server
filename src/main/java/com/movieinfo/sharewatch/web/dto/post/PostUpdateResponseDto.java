package com.movieinfo.sharewatch.web.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class PostUpdateResponseDto {
    private boolean success;
    private String message;

    public PostUpdateResponseDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
