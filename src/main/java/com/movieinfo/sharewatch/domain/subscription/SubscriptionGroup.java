package com.movieinfo.sharewatch.domain.subscription;

import com.movieinfo.sharewatch.domain.review.Review;
import com.movieinfo.sharewatch.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Builder.Default
    @OneToMany(mappedBy = "subGroup", cascade = ALL, orphanRemoval = true)
    private List<User> UserList = new ArrayList<>();

    @OneToOne(mappedBy = "subGroup", cascade = ALL, orphanRemoval = true)
    private Subscription subscription;

    public void addUser(User user){
        UserList.add(user);
    }

    public void addGroup(Subscription subscription) {
        this.subscription = subscription;
    }
}
