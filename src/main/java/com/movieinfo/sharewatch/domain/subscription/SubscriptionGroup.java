package com.movieinfo.sharewatch.domain.subscription;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movieinfo.sharewatch.domain.review.Review;
import com.movieinfo.sharewatch.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "subscriptionGroup")
public class SubscriptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subGroup_id")
    private Long subGroupId;

    @OneToMany(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<User> UserList = new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy = "subGroup", cascade = ALL, orphanRemoval = true)
    private Subscription subscription;

    public void addUser(User user){UserList.add(user);
        System.out.println("======================== 유저 등록 함수 실행");}

    public void deleteUser(User user){UserList.remove(user);}

    public void addGroup(Subscription subscription) {
        this.subscription = subscription;
    }
}
