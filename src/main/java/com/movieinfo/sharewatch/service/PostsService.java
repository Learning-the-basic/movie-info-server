package com.movieinfo.sharewatch.service;

import com.movieinfo.sharewatch.domain.posts.PostsRepository;
import com.movieinfo.sharewatch.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getPost_id();
    }
}
