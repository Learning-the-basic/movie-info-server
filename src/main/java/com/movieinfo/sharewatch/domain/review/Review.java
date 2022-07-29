package com.movieinfo.sharewatch.domain.review;

import com.movieinfo.sharewatch.domain.BaseTimeEntity;
import com.movieinfo.sharewatch.domain.posts.Status;
import com.movieinfo.sharewatch.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_no")
    private Long reviewId;//리뷰 아이디

    @ManyToOne(fetch = FetchType.LAZY)//지연 로딩
    @JoinColumn(name = "user_id")
    private User user;//작성자 회원번호

    @Column(name = "r_type", nullable = false)
    private int reviewType;//리뷰 타입 - 1: 짧은 글 , 2: 긴글

    //@Column(name = "ref_m_no", nullable = false)
    //private int refMNo;//참조 영화 번호

    @Column(name = "score", nullable = false, columnDefinition = "integer default 0")
    private Double movieScore;//별점

    @Lob//대용량 데이터
    @Column(name="reviewContent", nullable = false)
    private String reviewContent;//리뷰 내용

    @Column
    private int like;//리뷰 좋아요 갯수

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Review(Long writer_id, String reviewContent, Double movieScore, int reviewType, Status status){
        this.reviewContent = reviewContent;
        this.movieScore = movieScore;
        this.reviewType = reviewType;
       // this.refMNo = refMNo;
        this.status = status;
    }

    public void confirmWriter(User user){
        this.user = user;
        user.addReview(this);
    }

    //댓글 수정
    public void updateReview(String reviewContent){this.reviewContent = reviewContent;}
    //별점 수정
    public void updateMovieScore(Double movieScore){this.movieScore = movieScore;}
    //타입 변경
    public void updateReviewType(int reviewType){this.reviewType = reviewType;}
}
