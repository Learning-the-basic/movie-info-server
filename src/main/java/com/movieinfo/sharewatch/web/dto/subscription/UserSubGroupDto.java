package com.movieinfo.sharewatch.web.dto.subscription;

import com.movieinfo.sharewatch.domain.subscription.UserSubGroup;
import com.movieinfo.sharewatch.web.dto.user.UserDto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserSubGroupDto {

    private Long id;

    private UserDto user;

    private SubScriptionGroupDto subGroup;

    public static UserSubGroupDto toDto(UserSubGroup userSubGroup) {
        return new UserSubGroupDto(userSubGroup.getId(), UserDto.toDto(userSubGroup.getUser())
                , SubScriptionGroupDto.toDto(userSubGroup.getSubGroup()));
    }


//    public static Object toDto(List<UserSubGroup> userList, List<UserSubGroup> subGroup) {
//        return new UserSubGroupDto[]()
//    }
}
