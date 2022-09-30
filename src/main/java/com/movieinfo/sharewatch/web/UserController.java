package com.movieinfo.sharewatch.web;

import com.movieinfo.sharewatch.security.CurrentUser;
import com.movieinfo.sharewatch.security.UserPrincipal;
import com.movieinfo.sharewatch.service.UserService;
import com.movieinfo.sharewatch.web.dto.user.UserDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "User Controller", tags = "User")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserDto getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return UserDto.toDto(
                userService.findById(userPrincipal.getId())
        );
    }
}