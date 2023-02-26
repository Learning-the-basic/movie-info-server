package com.movieinfo.sharewatch.auth;



import com.movieinfo.sharewatch.auth.dto.GoogleOAuth2UserInfo;
import com.movieinfo.sharewatch.auth.dto.OAuth2UserInfo;
import com.movieinfo.sharewatch.exception.OAuth2AuthenticationProcessingException;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}