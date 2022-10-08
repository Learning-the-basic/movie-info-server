package com.movieinfo.sharewatch.web.dto.subscription;

import com.movieinfo.sharewatch.domain.subscription.Subscription;
import com.movieinfo.sharewatch.domain.subscription.SubscriptionGroup;
import com.movieinfo.sharewatch.domain.subscription.UserSubGroup;
import com.movieinfo.sharewatch.domain.user.User;
import com.movieinfo.sharewatch.web.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubScriptionGroupDto {

    private Long subGroupId;

    //private List<UserSubGroupDto> userList;
    public static SubScriptionGroupDto toDto(SubscriptionGroup subGroup) {
        return new SubScriptionGroupDto(subGroup.getSubGroupId()/*, subGroup.getUserList().stream().map(UserSubGroupDto::toDto).collect(Collectors.toList())*/);

    }

}
