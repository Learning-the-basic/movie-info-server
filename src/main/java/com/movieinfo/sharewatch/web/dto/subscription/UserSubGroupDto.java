package com.movieinfo.sharewatch.web.dto.subscription;

import com.movieinfo.sharewatch.domain.subscription.UserSubGroup;
import com.movieinfo.sharewatch.web.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSubGroupDto {

    private UserDto user;

    private SubScriptionGroupDto subGroupDto;

    public UserSubGroupDto toDto(UserSubGroup userSubGroup){
        return new UserSubGroupDto(UserDto.toDto(userSubGroup.getUser())
                   , SubScriptionGroupDto.toDto(userSubGroup.getSubGroup()));
    }
}
