package com.movieinfo.sharewatch.web;

import com.movieinfo.sharewatch.domain.user.User;
import com.movieinfo.sharewatch.service.PostsService;
import com.movieinfo.sharewatch.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;
    @PostMapping("/api/posts")
    public Long save(@Valid @ModelAttribute PostsSaveRequestDto requestDto){

        return postsService.save(requestDto);
    }
}
