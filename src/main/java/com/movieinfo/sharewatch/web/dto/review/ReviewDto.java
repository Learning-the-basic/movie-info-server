package com.movieinfo.sharewatch.web.dto.review;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movieinfo.sharewatch.domain.review.Review;
import com.movieinfo.sharewatch.web.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ReviewDto {

    private Long reviewId ;
    private Double movieScore;
    private String reviewContent;//리뷰 내용

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime modifiedAt;

    private UserDto user;

    public static ReviewDto toDto(Review review) {
        return new ReviewDto(
                review.getReviewId(),
                review.getMovieScore(),
                review.getReviewContent(),
                review.getCreatedDate(),
                review.getModifiedDate(),
                UserDto.toDto(review.getUser())
        );
    }

}
