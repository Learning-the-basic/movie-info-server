package com.movieinfo.sharewatch.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieinfo.sharewatch.domain.posts.Status;
import com.movieinfo.sharewatch.domain.posts.Posts;
import com.movieinfo.sharewatch.domain.posts.PostsRepository;
import com.movieinfo.sharewatch.web.dto.PostsSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @BeforeEach
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

    }

    @AfterEach
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    @WithMockUser(roles = "USER")
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

        //ResponseEntity<Object> responseEntity=restTemplate.postForEntity(url,requestDto,Object.class);
        //System.out.println(responseEntity.toString());
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then

        List<Posts> all=postsRepository.findAll();

        assertEquals(all.get(0).getTitle(),title);
        assertEquals(all.get(0).getContent(),content);

    }
}