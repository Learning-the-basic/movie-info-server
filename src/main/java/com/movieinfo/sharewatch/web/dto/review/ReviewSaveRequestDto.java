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

    private String refMNo;//참조 영화 번호
    private String reftype;//참조 분류
    private Double movieScore;//별점
    private String reviewContent;//리뷰 내용
    private Status status;//상태값

    @Builder
    public ReviewSaveRequestDto(String reftype, String refMNo, Double movieScore, String reviewContent, Status status){
        this.reftype = reftype;
        this.refMNo  = refMNo;
        this.movieScore = movieScore;
        this.reviewContent = reviewContent;
        this.status = status;
    }

    //Review타입으로 저장을 해놓는 것이다.
    public Review toEntity(){
        return Review.builder()
                .reftype(reftype)
                .refMNo(refMNo)
                .movieScore(movieScore)
                .reviewContent(reviewContent)
                .status(status)
                .build();

    }
}
