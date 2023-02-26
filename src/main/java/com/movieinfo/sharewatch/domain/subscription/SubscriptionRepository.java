package com.movieinfo.sharewatch.domain.subscription;

import com.movieinfo.sharewatch.domain.posts.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription,Long>{
    @Query(value = "SELECT sub FROM Subscription sub WHERE sub.id = :id AND sub.status = :status")
    Subscription findByStatusAndId(@Param("id") Long id, @Param("status") Status status);

    Page<Subscription> findAllByStatus(Pageable pageable, Status status);
}
