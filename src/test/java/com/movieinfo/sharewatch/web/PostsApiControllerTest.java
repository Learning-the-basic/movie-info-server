package com.movieinfo.sharewatch.web;

import com.movieinfo.sharewatch.domain.posts.Status;
import com.movieinfo.sharewatch.domain.posts.Posts;
import com.movieinfo.sharewatch.domain.posts.PostsRepository;
import com.movieinfo.sharewatch.web.dto.PostsSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @Test
    void Posts_등록() throws Exception {
        //given
        String title="test title";
        String content="content...";

        PostsSaveRequestDto requestDto=PostsSaveRequestDto.
                builder()
                .title(title)
                .content(content)
                .writer_id((long)111)
                .status(Status.Y)
                .build();

        String url="http://localhost:"+port+"/api/posts";

        //when
        ResponseEntity<Object> responseEntity=restTemplate.postForEntity(url,requestDto,Object.class);
        System.out.println(responseEntity.toString());

        //then
        assertEquals( HttpStatus.OK,responseEntity.getStatusCode());

        List<Posts> all=postsRepository.findAll();


        //assertEquals(all.get(0).getTitle(),title);
        //assertEquals(all.get(0).getContent(),content);

    }
}