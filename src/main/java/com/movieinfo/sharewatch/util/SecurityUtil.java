package com.movieinfo.sharewatch.util;

import com.movieinfo.sharewatch.security.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;


public class SecurityUtil {
    public static String getLoginUsername(){

        // TODO: 이곳에, 로그인한 유저가 있으면 반환, 없으면 예외 발생
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userPrincipal.getUsername();
    }

}