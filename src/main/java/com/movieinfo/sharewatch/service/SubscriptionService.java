package com.movieinfo.sharewatch.service;

import com.movieinfo.sharewatch.domain.posts.Status;
import com.movieinfo.sharewatch.domain.subscription.*;
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

    private final UserSubGroupRepository userSubGroupRepository;

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
    public SubscriptionDto selectSubscription(Long id){

        //Subscription sub = subRepository.findById(id).orElseThrow(()-> new RuntimeException("찾고자 하는 게시글이 없습니다."));
        Status status = Status.Y;
        Subscription sub = subRepository.findByStatus(status);

        // 조회수 업데이트
        //sub.increaseCount();
        subRepository.updateCount(id);

        return SubscriptionDto.toDto(sub);
    }

//    @Transactional
//    public String createSubscription(SubscriptionDto.SubSaveRequestDto subRequestDto){
//
//        User user = userRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(()-> new UserException());
//
//        Long groupId = createSubGroup(user);
//
//        SubscriptionGroup subGroup = subGroupRepository.findById(groupId).orElseThrow(()-> new RuntimeException());
//
//        Subscription sub = subRequestDto.toEntity();
//
//        sub.confirmWriter(user);
//        sub.bindGroup(subGroup);
//        //user.enterSubGroup(subGroup);
//        sub.increaseMemberCount();
//        return subRepository.save(sub).getTitle();
//    }

    @Transactional
    public String createSubscription(SubscriptionDto.SubSaveRequestDto subRequestDto){

        User user = userRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(()-> new UserException());

        Long groupId = createSubGroup();

        SubscriptionGroup subGroup = subGroupRepository.findById(groupId).orElseThrow(()-> new RuntimeException());

        Subscription sub = subRequestDto.toEntity();

        sub.confirmWriter(user);
        sub.bindGroup(subGroup);
        //user.enterSubGroup(subGroup);
        mappingUserAndGroup(user, subGroup);
        sub.increaseMemberCount();
        return subRepository.save(sub).getUser().toString();
    }

    private void mappingUserAndGroup(User user, SubscriptionGroup subGroup) {

        UserSubGroup us = new UserSubGroup();
        us.mappingUserAndGroup(user, subGroup);

        userSubGroupRepository.save(us);
    }


//    @Transactional
//    public Long createSubGroup(User user) {
//
//        SubscriptionGroup subGroup = new SubscriptionGroup();
//
//        user.enterSubGroup(subGroup);
//        subGroup.addUser(user);
//        return subGroupRepository.save(subGroup).getSubGroupId();
//    }

        @Transactional
    public Long createSubGroup() {

        SubscriptionGroup subGroup = new SubscriptionGroup();

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

//    @Transactional
//    public List<User> selectSubscriptionGroup(Long id) {
//        return subGroupRepository.findById(id).orElseThrow(RuntimeException::new).getUserList();
//    }
    @Transactional
    public void insertSubscriptionGroupUser(Long id) {
        User user = userRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(()-> new UserException());

        Optional<SubscriptionGroup> subGroup = Optional.ofNullable(subGroupRepository.findById(id).orElseThrow(RuntimeException::new));
        if(subGroup.isPresent()){

            SubscriptionGroup subscription = subGroup.get();

            //user.EnterSubGroup(subscription);
            //subscription.addUser(user);

            mappingUserAndGroup(user, subscription);

            subscription.getSubscription().increaseMemberCount();
        }
    }


//    @Transactional
//    public List<User> deleteSubscriptionGroupUser(Long id) {
//        User user = userRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(()-> new UserException());
//
//        SubscriptionGroup subGroup = subGroupRepository.findById(id).orElseThrow(()-> new RuntimeException());
//        System.out.println(subGroup.getUserList());
//        subGroup.deleteUser(user);
//
//        return subGroup.getUserList();
//
//    }


}
