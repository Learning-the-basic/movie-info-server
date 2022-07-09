package com.movieinfo.sharewatch.service;

import com.movieinfo.sharewatch.domain.posts.Posts;
import com.movieinfo.sharewatch.domain.posts.PostsRepository;
import com.movieinfo.sharewatch.domain.user.User;
import com.movieinfo.sharewatch.domain.user.UserRepository;
import com.movieinfo.sharewatch.exception.user.UserException;
import com.movieinfo.sharewatch.util.SecurityUtil;
import com.movieinfo.sharewatch.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){

        Posts post=requestDto.toEntity();

        post.confirmWriter(userRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(()-> new UserException()));

        return postsRepository.save(post).getPostId();
    }


}
