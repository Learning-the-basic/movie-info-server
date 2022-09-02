package com.movieinfo.sharewatch.web.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movieinfo.sharewatch.domain.posts.Posts;
import com.movieinfo.sharewatch.domain.user.User;
import com.movieinfo.sharewatch.web.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime modifiedAt;

    private UserDto user;


    public static PostDto toDto(Posts post) {
        return new PostDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedDate(),
                post.getModifiedDate(),
                UserDto.toDto(post.getUser())
        );
    }
}

