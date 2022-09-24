package com.movieinfo.sharewatch.domain.subscription;

import com.movieinfo.sharewatch.domain.posts.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {

    @Modifying
    @Query("update Subscription  sub set sub.count = sub.count +1 where sub.Id = :id")
    void updateCount(Long id);


    @Modifying
    @Query("select Subscription from Subscription where Subscription.status = 'Y' and Subscription.Id = :id")
    Subscription selectSubscription(Long id);

    //public Subscription findByIdAndStatus(Long id, Status status);

}
