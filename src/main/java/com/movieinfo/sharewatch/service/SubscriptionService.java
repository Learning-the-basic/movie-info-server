package com.movieinfo.sharewatch.service;

import com.movieinfo.sharewatch.domain.subscription.Subscription;
import com.movieinfo.sharewatch.domain.subscription.SubscriptionRepository;
import com.movieinfo.sharewatch.domain.user.UserRepository;
import com.movieinfo.sharewatch.exception.user.UserException;
import com.movieinfo.sharewatch.util.SecurityUtil;
import com.movieinfo.sharewatch.web.dto.post.PostsSaveRequestDto;
import com.movieinfo.sharewatch.web.dto.subscription.SubscriptionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SubscriptionService {

    private final SubscriptionRepository subRepository;
    private final UserRepository userRepository;

    @Transactional
    public String insertSubscription(SubscriptionDto.SubSaveRequestDto subRequestDto){

        Subscription sub = subRequestDto.toEntity();

        sub.confirmWriter(userRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(()-> new UserException()));

        return subRepository.save(sub).getTitle();
    }

    @Transactional
    public SubscriptionDto findSubscription(Long id) {

        Subscription sub = subRepository.findById(id).orElseThrow(RuntimeException::new);

        // 조회수 업데이트
        sub.increaseCount();

        return SubscriptionDto.toDto(subRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Transactional
    public void updateSubscription(Long post_id, SubscriptionDto.SubUpdateRequestDto sReq) {
        Optional<Subscription> sub = Optional.ofNullable(subRepository.findById(post_id).orElseThrow(RuntimeException::new));

        if(sub.isPresent()){
            Subscription subscription = sub.get();

            subscription.changeSub(sReq.getTitle(), sReq.getContent(), sReq.getSubService(), sReq.getSubCharge(), sReq.getSubPeriod());
        }

        //return new PostUpdateResponse(post_id);
    }

    @Transactional
    public void delete(Long id) {
        Subscription sub = subRepository.findById(id).orElseThrow(RuntimeException::new);

        sub.delete();

    }

    @Transactional
    public List<SubscriptionDto> selectSubscriptionList() {

        List<Subscription> subscriptions = subRepository.findAll();

        List<SubscriptionDto> subscriptionDtos = new ArrayList<>();

        for(Subscription sub: subscriptions){

            SubscriptionDto subDto = SubscriptionDto.toDto(sub);

            subscriptionDtos.add(subDto);
        }
            return subscriptionDtos;
    }
}
