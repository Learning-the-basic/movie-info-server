package com.movieinfo.sharewatch.web.dto.subscription;

import com.movieinfo.sharewatch.domain.subscription.SubscriptionGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
