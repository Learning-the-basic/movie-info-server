package com.movieinfo.sharewatch.domain.subscription;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SubscriptionGroups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subGroup_id")
    private Long subGroupId;

    @JoinColumn(name = "user_id")
    private Long founderId;

    @JoinColumn(name = "user_id")
    private Long userId;
}
