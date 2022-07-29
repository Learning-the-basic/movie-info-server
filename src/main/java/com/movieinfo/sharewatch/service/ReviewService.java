package com.movieinfo.sharewatch.service;

import com.movieinfo.sharewatch.domain.posts.Posts;
import com.movieinfo.sharewatch.domain.review.Review;
import com.movieinfo.sharewatch.domain.review.ReviewRepository;
import com.movieinfo.sharewatch.domain.user.UserRepository;
import com.movieinfo.sharewatch.exception.user.UserException;
import com.movieinfo.sharewatch.util.SecurityUtil;
import com.movieinfo.sharewatch.web.dto.post.PostUpdateResponse;
import com.movieinfo.sharewatch.web.dto.review.ReivewUpdateResponse;
import com.movieinfo.sharewatch.web.dto.review.ReviewSaveRequestDto;
import com.movieinfo.sharewatch.web.dto.review.ReviewUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public  Long saveReview(ReviewSaveRequestDto reviewSaveRequestDto){
        Review review = reviewSaveRequestDto.toEntity();
        review.confirmWriter(userRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(()-> new UserException()));
        return reviewRepository.save(review).getReviewId();
    }

    @Transactional
    @PreAuthorize("@postGuard.check(#id)")
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(RuntimeException::new);
        reviewRepository.delete(review);
    }

    public ReivewUpdateResponse updateReview(Long id, ReviewUpdateResponse ruq) {
        Review review = reviewRepository.findById(id).orElseThrow(RuntimeException::new);

        //리뷰 내용
        ruq.getReviewContent().ifPresent(review::updateReview);
        //리뷰 별점
        ruq.getMovieScore().ifPresent(review::updateMovieScore);
        //리뷰 타입
        ruq.getReviewType().ifPresent(review::updateReviewType);

        return new ReivewUpdateResponse(id);
    }
}
