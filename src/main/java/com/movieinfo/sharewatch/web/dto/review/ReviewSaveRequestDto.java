package com.movieinfo.sharewatch.web.dto.review;

import com.movieinfo.sharewatch.domain.posts.Status;
import com.movieinfo.sharewatch.domain.review.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewSaveRequestDto {

    private int reviewType;//리뷰 타입
   // private int refMNo;//참조 영화 번호
    private Double movieScore;//별점
    private String reviewContent;//리뷰 내용
    private Status status;//상태값

    @Builder
    public ReviewSaveRequestDto(int reviewType, double movieScore, String reviewContent, Status status){
        this.reviewType = reviewType;
        //this.refMNo  = refMNo;
        this.movieScore = movieScore;
        this.reviewContent = reviewContent;
        this.status = status;
    }

    //Review타입으로 저장을 해놓는 것이다.
    public Review toEntity(){
        Review review = Review.builder()
                .reviewType(reviewType)
                .movieScore(movieScore)
                .reviewContent(reviewContent)
                .status(status)
                .build();
        return review;
    }
}
