package com.movieinfo.sharewatch.domain.subscription;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class SubscriptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_group_id")
    private Long subGroupId;

    @OneToMany(mappedBy = "subGroup")
    private List<UserSubGroup> userList = new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy = "subGroup", cascade = ALL, orphanRemoval = true)
    private Subscription subscription;

    public void addPost(Subscription subscription) {
        this.subscription = subscription;
    }


}
