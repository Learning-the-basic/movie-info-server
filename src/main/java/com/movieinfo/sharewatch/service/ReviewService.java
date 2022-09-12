package com.movieinfo.sharewatch.service;

import com.movieinfo.sharewatch.domain.posts.Posts;
import com.movieinfo.sharewatch.domain.review.Review;
import com.movieinfo.sharewatch.domain.review.ReviewRepository;
import com.movieinfo.sharewatch.domain.user.User;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.sampled.ReverbType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Boolean deleteReview(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isPresent()) {
            reviewRepository.delete(review.get());
        }
        return true;
    }

    @Transactional
    public ReivewUpdateResponse updateReview(Long id, ReviewUpdateResponse ruq) {
        Review review = reviewRepository.findById(id).orElseThrow(RuntimeException::new);

        //이부분 다시 체크 필요!!
        //리뷰 내용
        ruq.getReviewContent().ifPresent(review::updateReview);
        //리뷰 별점
        ruq.getMovieScore().ifPresent(review::updateMovieScore);
        //리뷰 타입
        ruq.getReftype().ifPresent(review::updateRefType);

        return new ReivewUpdateResponse(id);
    }
    @Transactional
    public ReviewDto read(long id) {

        Optional<Review> entity = reviewRepository.findById((long)id);

        if(entity.isPresent()){
            Review review = entity.get();
            ReviewDto reviewDto = ReviewDto.builder()
                    .reviewId(review.getReviewId())
                    .movieScore(review.getMovieScore())
                    .reviewContent(review.getReviewContent())
                    .refMNo(review.getRefMNo())
                    .reftype(review.getReftype())
                    .user(UserDto.toDto(review.getUser()))
                    .createdAt(review.getCreatedDate())
                    .build();

            review.increaseWatch();
            return reviewDto;
        }

        return null;
    }

    @Transactional
    /*public List<ReviewDto> selectReviewAll(){

        List<Review> reviews = reviewRepository.findAll();

        List<ReviewDto> reviewDtoList = new ArrayList<>();

        for(Review review : reviews){
            ReviewDto dto = ReviewDto.builder()
                    .reviewId(review.getReviewId())
                    .reftype(review.getReftype())
                    .refMNo(review.getRefMNo())
                    .movieScore(review.getMovieScore())
                    .reviewContent(review.getReviewContent())
                    .count(review.getCount())
                    .user(UserDto.toDto(review.getUser()))
                    .createdAt(review.getCreatedDate())
                    .build();
            reviewDtoList.add(dto);
        }
        return reviewDtoList;
    }*/
    public Page<ReviewDto> selectReviewList(int page){

        return reviewRepository.findAll(PageRequest.of(page,5, Sort.by(Sort.Direction.DESC,"reviewId"))).map(ReviewDto::toDto);
    }

    @Transactional
    public List<ReviewDto> selectReviewAllMy(String movieMn){

        List<Review> reviews = reviewRepository.findAll();

        List<ReviewDto> reviewDtoList = new ArrayList<>();

        String movieMninfo = movieMn;
        for(Review review : reviews){
            if(review.getRefMNo().equals(movieMninfo)) {
                ReviewDto dto = ReviewDto.builder()
                        .reviewId(review.getReviewId())
                        .reftype(review.getReftype())
                        .refMNo(review.getRefMNo())
                        .movieScore(review.getMovieScore())
                        .reviewContent(review.getReviewContent())
                        .count(review.getCount())
                        .user(UserDto.toDto(review.getUser()))
                        .createdAt(review.getCreatedDate())
                        .build();
                reviewDtoList.add(dto);
            }else{
                continue;
            }

        }
        return reviewDtoList;
    }
}
