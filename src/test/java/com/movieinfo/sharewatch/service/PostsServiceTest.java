package com.movieinfo.sharewatch.service;

import com.movieinfo.sharewatch.domain.posts.Status;
import com.movieinfo.sharewatch.web.dto.PostsSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostsServiceTest {

    @Autowired
    PostsService postsService;

    @Test
    void post저장_service() {
        //given
        String title="post service title";
        String content="content...";

        PostsSaveRequestDto requestDto=PostsSaveRequestDto.
                builder()
                .title(title)
                .content(content)
                .writer_id((long)111)
                .status(Status.Y)
                .build();

        System.out.println(postsService.save(requestDto));


    }
}