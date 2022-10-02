package com.movieinfo.sharewatch.web.dto.subscription;

import com.movieinfo.sharewatch.domain.subscription.SubscriptionGroup;
import com.movieinfo.sharewatch.domain.subscription.UserSubGroup;
import com.movieinfo.sharewatch.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubScriptionGroupDto {

    private Long subGroupId;

    //private List<User> userList;
    private  List<UserSubGroup> userList;
    public static SubScriptionGroupDto toDto(SubscriptionGroup subGroup) {
        return new SubScriptionGroupDto(subGroup.getSubGroupId(), subGroup.getUserList());

    }

}
