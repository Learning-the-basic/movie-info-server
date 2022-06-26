package com.movieinfo.sharewatch.web.dto;

import com.movieinfo.sharewatch.domain.posts.Status;
import com.movieinfo.sharewatch.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private Long writer_id;
    private String content;
    private Status status;

    @Builder
    public PostsSaveRequestDto(String title, Long writer_id, String content, Status status) {
        this.title = title;
        this.writer_id = writer_id;
        this.content = content;
        this.status = status;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .writer_id(writer_id)
                .content(content)
                .status(Status.Y)
                .build();
    }
}
