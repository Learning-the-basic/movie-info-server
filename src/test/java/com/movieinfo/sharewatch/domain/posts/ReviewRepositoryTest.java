package com.movieinfo.sharewatch.domain.posts;

import com.movieinfo.sharewatch.domain.review.ReviewRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReviewRepositoryTest {
    @Autowired
    ReviewRepository reviewRepository;

    @AfterEach
    public void clean(){reviewRepository.deleteAll();}

    @Test
    public void review저장(){
        //given
        //String
    }

}
