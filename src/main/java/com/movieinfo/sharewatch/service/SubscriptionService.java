package com.movieinfo.sharewatch.service;

import com.movieinfo.sharewatch.domain.posts.Status;
import com.movieinfo.sharewatch.domain.subscription.Subscription;
import com.movieinfo.sharewatch.domain.subscription.SubscriptionGroup;
import com.movieinfo.sharewatch.domain.subscription.SubscriptionGroupRepository;
import com.movieinfo.sharewatch.domain.subscription.SubscriptionRepository;
import com.movieinfo.sharewatch.domain.user.User;
import com.movieinfo.sharewatch.domain.user.UserRepository;
import com.movieinfo.sharewatch.exception.user.UserException;
import com.movieinfo.sharewatch.util.SecurityUtil;
import com.movieinfo.sharewatch.web.dto.subscription.SubscriptionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SubscriptionService {

    private final SubscriptionRepository subRepository;
    private final UserRepository userRepository;

    private final SubscriptionGroupRepository subGroupRepository;

    @Transactional
    public Page<SubscriptionDto> selectSubscriptionList(int page) {

        return subRepository.findAll(PageRequest.of(page, 3)).map(SubscriptionDto::toDto);
    }
/*
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
*/
    @Transactional
    public SubscriptionDto selectSubscription(Long id) {

        Subscription sub = subRepository.selectSubscription(id);

        // 조회수 업데이트
        //sub.increaseCount();
        subRepository.updateCount(id);

        return SubscriptionDto.toDto(sub);
    }

    @Transactional
    public String createSubscription(SubscriptionDto.SubSaveRequestDto subRequestDto){

        User user = userRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(()-> new UserException());

        Long groupId = createSubGroup(user);

        SubscriptionGroup subGroup = subGroupRepository.findById(groupId).orElseThrow(()-> new RuntimeException());

        Subscription sub = subRequestDto.toEntity();

        sub.confirmWriter(user);
        sub.bindGroup(subGroup);
        sub.increaseMemberCount();
        return subRepository.save(sub).getTitle();
    }

    private Long createSubGroup(User user) {

        SubscriptionGroup subGroup = new SubscriptionGroup();

        subGroup.addUser(user);
        return subGroupRepository.save(subGroup).getSubGroupId();
    }


    @Transactional
    public void updateSubscription(Long post_id, SubscriptionDto.SubUpdateRequestDto sReq) {
        Optional<Subscription> sub = Optional.ofNullable(subRepository.findById(post_id).orElseThrow(RuntimeException::new));

        if(sub.isPresent()){
            Subscription subscription = sub.get();

            subscription.changeSub(sReq.getTitle(), sReq.getContent(), sReq.getSubService(), sReq.getSubCharge(), sReq.getSubMemLimit());
        }

    }

    @Transactional
    public void deleteSubscription(Long id) {
        Subscription sub = subRepository.findById(id).orElseThrow(RuntimeException::new);

        sub.delete();

    }

    @Transactional
    public List<User> selectSubscriptionGroup(Long id) {
        return subGroupRepository.findById(id).orElseThrow(RuntimeException::new).getUserList();
    }
    @Transactional
    public void insertSubscriptionGroupUser(Long id) {
        System.out.println("=========================================   서비스는 찎냐?");
        User user = userRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(()-> new UserException());

        //SubscriptionGroup subGroup = subGroupRepository.findById(id).orElseThrow(()-> new RuntimeException());

        Optional<SubscriptionGroup> subGroup = Optional.ofNullable(subGroupRepository.findById(id).orElseThrow(RuntimeException::new));
        System.out.println("==========================================   그룹 조회 끝");
        if(subGroup.isPresent()){
            System.out.println(" ============================================ if문 입장");
            SubscriptionGroup subscription = subGroup.get();
            //user.EnterSubGroup(subscription);
            subscription.addUser(user);
            subscription.getSubscription().increaseMemberCount();
            System.out.println("================================================== 유저 등록 성공");
        }
    }

    public List<User> deleteSubscriptionGroupUser(Long id) {
        User user = userRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(()-> new UserException());

        SubscriptionGroup subGroup = subGroupRepository.findById(id).orElseThrow(()-> new RuntimeException());
        System.out.println(subGroup.getUserList());
        subGroup.deleteUser(user);
        //subGroup.getUserList().;


        return subGroup.getUserList();

    }
}
