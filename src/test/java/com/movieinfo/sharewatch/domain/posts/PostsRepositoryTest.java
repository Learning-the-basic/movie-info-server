/*
package com.movieinfo.sharewatch.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup(){
        postsRepository.deleteAll();
    }
/*
    @Test
    public void post저장(){
        //given
        String title="resposit_test";
        String content="reposit_content";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .writer_id((long)111)
                .status(Status.Y)
                .build() );

        //when
        List<Posts> postsList=postsRepository.findAll();

        //then
        Posts posts=postsList.get(0);
        assertEquals(posts.getTitle(),title);
    }

 */
}

