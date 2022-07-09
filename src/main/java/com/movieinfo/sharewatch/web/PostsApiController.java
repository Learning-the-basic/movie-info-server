package com.movieinfo.sharewatch.web;

import com.movieinfo.sharewatch.domain.user.User;
import com.movieinfo.sharewatch.service.PostsService;
import com.movieinfo.sharewatch.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;
    @PostMapping("/api/posts")
    public Long save(@ModelAttribute PostsSaveRequestDto requestDto){

        return postsService.save(requestDto);
    }
}
