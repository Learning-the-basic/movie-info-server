package com.movieinfo.sharewatch.domain.subscription;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movieinfo.sharewatch.domain.BaseTimeEntity;
import com.movieinfo.sharewatch.domain.posts.Status;
import com.movieinfo.sharewatch.domain.user.User;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicInsert
@ToString(exclude = "user")
public class Subscription extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_id")
    private Long id;

    @Column(length = 255,nullable = false, name = "sub_title")
    protected String title;

    @Column(columnDefinition = "TEXT",nullable = false, name = "sub_content")
    protected String content;

    @Column(columnDefinition = "integer default 0", name = "sub_count")
    private Integer count;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(1) default 'Y'")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "sub_service", nullable = false)         // 이용 서비스
    private String subService;

    @Column(name = "sub_charge", nullable = false)          // 요금 정보
    private int subCharge;

    @Column(name = "sub_mem_limit", nullable = false)       // 구독 인원 제한
    private int subMemLimit;

    @Column(name = "sub_mem_count")       // 현제 구독 인원
    private Integer subMemCount;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_group_id")
    private SubscriptionGroup subGroup;

    @Builder(builderClassName = "PostBuilder", builderMethodName = "PostBuilder")
    public Subscription(User user, String title, String content, int count, Status status,
                        String subService, int subMemLimit,int subMemCount, int subCharge, SubscriptionGroup subGroup){
        this.user = user;
        this.title = title;
        this.content = content;
        this.count = count;
        this.status = status;
        this.subCharge = subCharge;
        this.subService = subService;
        this.subMemLimit = subMemLimit;
        this.subMemCount = subMemCount;
        this.subGroup = subGroup;
    }

    public void bindGroup(SubscriptionGroup subGroup) {
        this.subGroup = subGroup;
        subGroup.addPost(this);
    }


    public void confirmWriter(User user) {
        this.user = user;
        user.addPost(this);
    }

    //== 내용 수정 ==//
    public void changeSub(String subTitle, String subContent, String subService, int subCharge, int subMemLimit){
        this.title = subTitle;
        this.content = subContent;
        this.subService = subService;
        this.subCharge = subCharge;
        this.subMemLimit = subMemLimit;
    }

    public void increaseMemberCount(){
        this.subMemCount++;
    }

    public void increaseCount() {
        this.count++;
    }

    public void delete() {
        this.status = Status.N;
    }

}
