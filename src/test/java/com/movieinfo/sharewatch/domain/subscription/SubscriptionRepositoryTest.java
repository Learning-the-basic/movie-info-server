package com.movieinfo.sharewatch.domain.subscription;

import com.movieinfo.sharewatch.domain.posts.Status;
import com.movieinfo.sharewatch.domain.user.User;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class SubscriptionRepositoryTest {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Test
    void insertSubscription(){

        String title = "title";
        String content = "SubContent";
        String subService = "netflix";
        int subCharge = 20000;
        LocalDateTime subPeriod = now();
        Status status = Status.Y;

        Subscription params = Subscription.PostBuilder()
                .title(title)
                .content(content)
                .subService(subService)
                .subCharge(subCharge)
                .subPeriod(subPeriod)
                .status(status)
                .build();

        subscriptionRepository.save(params);

        List<Subscription> list = subscriptionRepository.findAll();
        Subscription sub = list.get(0);

        assertThat(sub.getTitle()).as("값을 확인해주세요. 현재 값: %s", sub.getTitle()).isEqualTo(title);
        assertThat(sub.getContent()).as("값을 확인해주세요. 현재 값: %s", sub.getContent()).isEqualTo(content);

    }

}