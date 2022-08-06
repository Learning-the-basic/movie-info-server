package com.movieinfo.sharewatch.service;

import com.movieinfo.sharewatch.domain.posts.Posts;
import com.movieinfo.sharewatch.domain.review.Review;
import com.movieinfo.sharewatch.domain.review.ReviewRepository;
import com.movieinfo.sharewatch.domain.user.UserRepository;
import com.movieinfo.sharewatch.exception.user.UserException;
import com.movieinfo.sharewatch.util.SecurityUtil;
import com.movieinfo.sharewatch.web.dto.post.PostDto;
import com.movieinfo.sharewatch.web.dto.post.PostUpdateResponse;
import com.movieinfo.sharewatch.web.dto.review.ReivewUpdateResponse;
import com.movieinfo.sharewatch.web.dto.review.ReviewDto;
import com.movieinfo.sharewatch.web.dto.review.ReviewSaveRequestDto;
import com.movieinfo.sharewatch.web.dto.review.ReviewUpdateResponse;
import com.movieinfo.sharewatch.web.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public  Long saveReview(ReviewSaveRequestDto reviewSaveRequestDto){
        Review review = reviewSaveRequestDto.toEntity();
        review.confirmWriter(userRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(()-> new UserException("use not found")));
        return reviewRepository.save(review).getReviewId();
    }

    @Transactional
    public Boolean deleteReview(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isPresent()) {
            reviewRepository.delete(review.get());
        }
        return true;
    }

    public ReivewUpdateResponse updateReview(Long id, ReviewUpdateResponse ruq) {
        Review review = reviewRepository.findById(id).orElseThrow(RuntimeException::new);

        //리뷰 내용
        ruq.getReviewContent().ifPresent(review::updateReview);
        //리뷰 별점
        ruq.getMovieScore().ifPresent(review::updateMovieScore);
        return new ReivewUpdateResponse(id);
    }
    public ReviewDto read(long id) {
        Optional<Review> entity = reviewRepository.findById((long)id);
        if(entity.isPresent()){
            Review review = entity.get();
            ReviewDto reviewDto = ReviewDto.builder()
                    .reviewId(review.getReviewId())
                    .movieScore(review.getMovieScore())
                    .reviewContent(review.getReviewContent())
                    .user(UserDto.toDto(review.getUser()))
                    .createdAt(review.getCreatedDate())
                    .build();
            return reviewDto;
        }
        return null;
    }
}
