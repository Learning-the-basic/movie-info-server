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
    @Column(name = "review_id")
    private Long reviewId;//리뷰 아이디

    @ManyToOne(fetch = FetchType.LAZY)//지연 로딩
    @JoinColumn(name = "user_id")
    private User user;//작성자 회원번호

    @Column(name = "ref_type", nullable = false)
    private String reftype;//참조분류 (S:짧은글, L:긴글)

    @Column(name = "ref_m_no", nullable = false)
    private String refMNo;//참조 영화 번호

    @Column(name = "movie_score", nullable = false)
    private Double movieScore;//별점

    @Column(name="review_content", nullable = false,columnDefinition = "TEXT")
    private String reviewContent;//리뷰 내용

    @Column(columnDefinition = "integer default 0")
    private int count;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(1) default 'Y'")
    private Status status;

    @Builder
    public Review(Long writer_id, String reftype, String reviewContent, Double movieScore, Status status, String refMNo){
        this.reviewContent = reviewContent;
        this.movieScore = movieScore;
        this.count = 0;
        this.status = status;
        this.refMNo = refMNo;
        this.reftype = reftype;
    }

    public void confirmWriter(User user){
        this.user = user;
        user.addReview(this);
    }

    //댓글 수정
    public void updateReview(String reviewContent){this.reviewContent = reviewContent;}
    //별점 수정
    public void updateMovieScore(Double movieScore){this.movieScore = movieScore;}
    //타입 수정
    public void updateRefType(String reftype){this.reftype = reftype;}

    public void increaseWatch(){this.count++;}
}
