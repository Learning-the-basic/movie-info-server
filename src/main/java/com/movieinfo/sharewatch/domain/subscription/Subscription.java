package com.movieinfo.sharewatch.domain.subscription;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.movieinfo.sharewatch.domain.posts.Posts;
import com.movieinfo.sharewatch.domain.posts.Status;
import com.movieinfo.sharewatch.domain.user.User;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder(builderClassName = "BaseBuilder", builderMethodName = "BaseBuilder")
public class Subscription extends Posts {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "sub_service", nullable = false)         // 이용 서비스
    private String subService;

    @Column(name = "sub_period", nullable = false)          // 이용 기간
    //@Schema(description = "이용 기간", type = "LocalDateTime", example = "2022-08-04")
   // @DateTimeFormat(pattern = "yyyy-MM-dd")
   // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
   // @ApiModelProperty(required = true, example = "2021-08-20T00:00:00")
    private String subPeriod;

    @Column(name = "sub_charge", nullable = false)          // 요금 정보
    private int subCharge;

    @Column(name = "sub_mem_limit", nullable = false)       // 구독 인원 제한
    private int subMemLimit;

    @Column(name = "sub_mem_count", columnDefinition = "integer default 1")       // 현제 구독 인원
    private int subMemCount;

    @Builder(builderClassName = "PostBuilder", builderMethodName = "PostBuilder")
    public Subscription(Long id, User user, String title, String content, int count, Status status,
                        String subService, String subPeriod, int subMemLimit,int subMemCount, int subCharge){
        super(id, user, title, content, count, status);
        this.subCharge = subCharge;
        this.subPeriod = subPeriod;
        this.subService = subService;
        this.subMemLimit = subMemLimit;
        this.subMemCount = subMemCount;
    }



    public void confirmWriter(User user) {
        this.user = user;
        user.addPost(this);
    }

    //== 내용 수정 ==//
    public void changeSub(String subTitle, String subContent, String subService, int subCharge, String subPeriod, int subMemLimit){
        this.title = subTitle;
        this.content = subContent;
        this.subService = subService;
        this.subCharge = subCharge;
        this.subPeriod = subPeriod;
        this.subMemLimit = subMemLimit;
    }




}
