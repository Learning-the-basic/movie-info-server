package com.movieinfo.sharewatch.domain.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movieinfo.sharewatch.config.auth.AuthProvider;
import com.movieinfo.sharewatch.domain.BaseTimeEntity;
import com.movieinfo.sharewatch.domain.posts.Posts;
import com.movieinfo.sharewatch.domain.review.Review;
import com.movieinfo.sharewatch.domain.subscription.Subscription;
import com.movieinfo.sharewatch.domain.subscription.SubscriptionGroup;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long Id;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false)
    private String email;

    @Column
    private String imageUrl;

    @JsonIgnore
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subGroup_id")
    private SubscriptionGroup subGroup;

    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)
    private List<Subscription> subList = new ArrayList<>();

    @Builder
    public User(String name, String email, String imageUrl, String password, AuthProvider provider, String providerId, Role role) {
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.password = password;
        this.provider = provider;
        this.providerId = providerId;
        this.role = role;
    }

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>();


    public void addPost(Subscription sub){
        //post의 writer 설정은 post에서 함
        subList.add(sub);
    }

    public User update(String name, String email, String imageUrl) {
        this.name = name;
        this.email=email;
        this.imageUrl=imageUrl;

        return this;
    }

    public void addReview(Review reivew){
        reviewList.add(reivew);
    }


    public String getRoleKey() {
        return this.role.getKey();
    }


    public void EnterSubGroup(SubscriptionGroup subGroup) {
        this.subGroup = subGroup;
        subGroup.addUser(this);
    }



}