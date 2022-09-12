package com.movieinfo.sharewatch.domain.subscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionGroupRepository extends JpaRepository<SubscriptionGroup,Long> {

}
