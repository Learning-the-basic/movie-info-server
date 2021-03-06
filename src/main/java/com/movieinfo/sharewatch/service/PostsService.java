package com.movieinfo.sharewatch.service;

import com.movieinfo.sharewatch.domain.posts.Posts;
import com.movieinfo.sharewatch.domain.posts.PostsRepository;
import com.movieinfo.sharewatch.domain.user.User;
import com.movieinfo.sharewatch.domain.user.UserRepository;
import com.movieinfo.sharewatch.exception.user.UserException;
import com.movieinfo.sharewatch.util.SecurityUtil;
import com.movieinfo.sharewatch.web.dto.post.PostDto;
import com.movieinfo.sharewatch.web.dto.post.PostUpdateRequest;
import com.movieinfo.sharewatch.web.dto.post.PostUpdateResponse;
import com.movieinfo.sharewatch.web.dto.post.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){

        Posts post=requestDto.toEntity();

        post.confirmWriter(userRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(()-> new UserException("use not found")));

        return postsRepository.save(post).getPostId();
    }

    public PostDto read(Long id) {
        return PostDto.toDto(postsRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Transactional
    public PostUpdateResponse update(Long post_id, PostUpdateRequest req) {
        Posts post = postsRepository.findById(post_id).orElseThrow(RuntimeException::new);

        req.getTitle().ifPresent(post::updateTitle);
        req.getContent().ifPresent(post::updateContent);

        return new PostUpdateResponse(post_id);
    }

    @Transactional
    public Boolean delete(Long id) {

        Optional<Posts> oPost = postsRepository.findById(id);
        if (oPost.isPresent()) {
            postsRepository.delete(oPost.get());
        }
        return true;
    }
}
