package com.movieinfo.sharewatch.web.dto.user;


import com.movieinfo.sharewatch.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String name;


    public static UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getName());
    }

    public static UserDto empty() {
        return new UserDto(null, "", "");
    }
}