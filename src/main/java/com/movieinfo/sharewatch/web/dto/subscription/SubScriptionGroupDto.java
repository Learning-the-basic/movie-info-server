package com.movieinfo.sharewatch.web.dto.subscription;

import com.movieinfo.sharewatch.domain.subscription.SubscriptionGroup;
import com.movieinfo.sharewatch.domain.user.User;
import com.movieinfo.sharewatch.web.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubScriptionGroupDto {

    private Long subGroupId;

    private List<User> userList;

    public static SubScriptionGroupDto toDto(SubscriptionGroup subGroup) {
        return new SubScriptionGroupDto(subGroup.getSubGroupId(), subGroup.getUserList());

    }

}
