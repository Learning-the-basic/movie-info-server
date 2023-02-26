package com.movieinfo.sharewatch.service;

import com.movieinfo.sharewatch.domain.posts.Status;
import com.movieinfo.sharewatch.domain.subscription.*;
import com.movieinfo.sharewatch.domain.user.User;
import com.movieinfo.sharewatch.domain.user.UserRepository;
import com.movieinfo.sharewatch.exception.user.UserException;
import com.movieinfo.sharewatch.util.SecurityUtil;
import com.movieinfo.sharewatch.web.dto.subscription.SubscriptionDto;
import com.movieinfo.sharewatch.web.dto.subscription.UserSubGroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SubscriptionService {

    private final SubscriptionRepository subRepository;
    private final UserRepository userRepository;

    private final SubscriptionGroupRepository subGroupRepository;

    private final UserSubGroupRepository userSubGroupRepository;

    @Transactional
    public Page<SubscriptionDto> selectSubscriptionList(int page) {

        Status status = Status.Y;

        return subRepository.findAllByStatus(PageRequest.of(page, 3, Sort.by(Sort.Direction.DESC, "Id")), status).map(SubscriptionDto::toDto);
    }

    @Transactional
    public SubscriptionDto selectSubscription(Long id) {

        Status status = Status.Y;
        Subscription sub = subRepository.findByStatusAndId(id, status);

        List<UserSubGroupDto> usList = userSubGroupRepository.findBySubGroup(sub.getSubGroup()).stream().map(UserSubGroupDto::toDto).collect(Collectors.toList());

        // 조회수 업데이트
        sub.increaseCount();

        SubscriptionDto subscriptionDto = SubscriptionDto.toDto(sub);
        subscriptionDto.setUserSubGroupList(usList);

        return subscriptionDto;
    }

    @Transactional
    public String createSubscription(SubscriptionDto.SubSaveRequestDto subRequestDto) {

        User user = userRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(UserException::new);

        Long groupId = createSubGroup();

        SubscriptionGroup subGroup = subGroupRepository.findById(groupId).orElseThrow(() -> new RuntimeException());

        Subscription sub = subRequestDto.toEntity();

        sub.confirmWriter(user);
        sub.bindGroup(subGroup);

        mappingUserAndGroup(user, subGroup);
        sub.increaseMemberCount();
        return subRepository.save(sub).toString();
    }

    private void mappingUserAndGroup(User user, SubscriptionGroup subGroup) {

        UserSubGroup us = new UserSubGroup();
        us.mappingUserAndGroup(user, subGroup);

        userSubGroupRepository.save(us);
    }

    @Transactional
    public Long createSubGroup() {

        SubscriptionGroup subGroup = new SubscriptionGroup();

        return subGroupRepository.save(subGroup).getSubGroupId();
    }

    @Transactional
    public void updateSubscription(Long post_id, SubscriptionDto.SubUpdateRequestDto sReq) {
        Optional<Subscription> sub = Optional.ofNullable(subRepository.findById(post_id).orElseThrow(RuntimeException::new));

        if (sub.isPresent()) {
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
    public void insertSubscriptionGroupUser(Long id) {
        User user = userRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(() -> new UserException());

        Optional<SubscriptionGroup> subGroup = Optional.ofNullable(subGroupRepository.findById(id).orElseThrow(RuntimeException::new));
        if (subGroup.isPresent()) {

            SubscriptionGroup subscription = subGroup.get();

            mappingUserAndGroup(user, subscription);

            subscription.getSubscription().increaseMemberCount();
        }
    }


    @Transactional
    public void deleteUserAndGroup(Long id) {
        User user = userRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(() -> new UserException());

        SubscriptionGroup subGroup = subGroupRepository.findById(id).orElseThrow(() -> new RuntimeException());

        UserSubGroup userSubGroup = new UserSubGroup();

        userSubGroup = userSubGroupRepository.findByUserAndSubGroup(user, subGroup);

        userSubGroupRepository.delete(userSubGroup);

    }

}
