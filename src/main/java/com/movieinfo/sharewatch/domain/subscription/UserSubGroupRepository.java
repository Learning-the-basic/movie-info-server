package com.movieinfo.sharewatch.domain.subscription;

import com.movieinfo.sharewatch.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSubGroupRepository extends JpaRepository<UserSubGroup, Long> {

    UserSubGroup findByUserAndSubGroup(User user, SubscriptionGroup subGroup);

    List<UserSubGroup> findBySubGroup(SubscriptionGroup subGroup);
}
