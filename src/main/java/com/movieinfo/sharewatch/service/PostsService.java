package com.movieinfo.sharewatch.service;

import com.movieinfo.sharewatch.domain.posts.PostsRepository;
import com.movieinfo.sharewatch.domain.user.User;
import com.movieinfo.sharewatch.domain.user.UserRepository;
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
    public Long save(PostsSaveRequestDto requestDto, User user){
        System.out.println(requestDto.getTitle()+"\n"+"11111");
        return postsRepository.save(requestDto.toEntity(user)).getPostId();
    }
}
