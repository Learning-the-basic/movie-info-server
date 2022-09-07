/*
package com.movieinfo.sharewatch.domain.subscription;

import com.movieinfo.sharewatch.domain.posts.Status;
import com.movieinfo.sharewatch.domain.user.User;
import com.movieinfo.sharewatch.web.dto.subscription.SubscriptionDto;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        String subPeriod = "2022-12-31";
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


    @Test
    void selectSubscriptionAll(){

        String title = "title";
        String content = "SubContent";
        String subService = "netflix";
        int subCharge = 20000;
        String subPeriod = "2022-12-31";
        Status status = Status.Y;

        // given
        List<Subscription> list = IntStream.rangeClosed(1,9).asLongStream().mapToObj(i ->{

            Subscription dto = Subscription.PostBuilder()
                    .title(title +i)
                    .content(content +i)
                    .subService(subService +i)
                    .subCharge(subCharge)
                    .subPeriod(subPeriod +i)
                    .build();
            return dto;

        }).collect(Collectors.toList());

        for(Subscription sub : list){

            subscriptionRepository.save(sub); // BoardDto 타입으로 저장이 불가능 하기 때문에 Entity의 형태로 변환해줘야함
        }

        // when
        long subsCount = subscriptionRepository.count(); // 카운트를 가져오는 쿼리문 // select count(*) as col_0_0_ from board board0_
        // count() 메소드의 반환형이 long이기 때문에 long으로 받아줘야 함

        System.out.println("조회수 " + subsCount);

        List<Subscription> subs = subscriptionRepository.findAll(); // 전체조회
       // List<Subscription> subs1 = subscriptionRepository.findAll(Sort.by(Sort.Direction.DESC, "post_id")); // Sort : 정렬방식 설정

        // then
        subs.stream().forEach(sub -> {
            System.out.println("sub === " + sub.getTitle());
        });
*/
/*
        subs1.stream().forEach(board -> {
            System.out.println("subs1 === " + subs1);
        });

 *//*

    }
    @Test
    void deleteSubscription(){

        // given
        String title = "title";
        String content = "SubContent";
        String subService = "netflix";
        int subCharge = 20000;
        String subPeriod = "2022-12-31";
        Status status = Status.Y;

        // given
        List<Subscription> list = IntStream.rangeClosed(1,9).asLongStream().mapToObj(i ->{

            Subscription dto = Subscription.PostBuilder()
                    .title(title +i)
                    .content(content +i)
                    .subService(subService +i)
                    .subCharge(subCharge)
                    .subPeriod(subPeriod +i)
                    .build();
            return dto;

        }).collect(Collectors.toList());

        for(Subscription sub : list){

            subscriptionRepository.save(sub); // BoardDto 타입으로 저장이 불가능 하기 때문에 Entity의 형태로 변환해줘야함
        }

        // Optional : 모든 결과값을 Optional이 받아서 null값이 아니면 메소드 안의 내용을 실행한다.
        Optional<Subscription> entity = subscriptionRepository.findById((long) 1); // 1에 해당하는 id를 가진 데이터가 있으면 그 값을 받아와서 담아줌
        entity.ifPresent(selectSub ->{ // ifPresent : null을 확인할 필요가 없이 값이 있으면 실행됨
            subscriptionRepository.delete(selectSub);
        });
*/
/*
        Board entity1 = boardRepository.findById((long) 2).get(); // entity1에는 찾아온 하나의 board타입 객체 정보가 들어가있음
        boardRepository.delete(entity1);
*//*


        //  boardRepository.deleteById(3l); // l : long타입 , 3l : id값이 3이고 자료형이 long인 컬럼 조회 후 삭제
    }


}*/
