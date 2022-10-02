package com.movieinfo.sharewatch.domain.subscription;

import com.movieinfo.sharewatch.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSubGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_group_id")
    private SubscriptionGroup subGroup;

    public void mappingUserAndGroup(User user, SubscriptionGroup subGroup) {
        this.user = user;
        this.subGroup = subGroup;
    }
}
