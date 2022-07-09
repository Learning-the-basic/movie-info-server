package com.movieinfo.sharewatch.web.dto;

import com.movieinfo.sharewatch.domain.posts.Status;
import com.movieinfo.sharewatch.domain.posts.Posts;
import com.movieinfo.sharewatch.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private Status status;

    @Builder
    public PostsSaveRequestDto(String title, String content, Status status) {
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .status(status)
                .build();
    }
}
