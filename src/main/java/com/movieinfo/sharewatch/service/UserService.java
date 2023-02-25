package com.movieinfo.sharewatch.service;

import com.movieinfo.sharewatch.domain.user.User;
import com.movieinfo.sharewatch.domain.user.UserRepository;
import com.movieinfo.sharewatch.exception.ResourceNotFoundException;
import com.movieinfo.sharewatch.web.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    //create
    public Long saveUser(User user){
        return userRepository.save(user).getId();
    }

    //read
    public User findById(Long userId){
        return ( userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId))
        );
    }

    //update
    public void updatename(Long userId,String name){
        User user=userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        user.update(name,user.getEmail(),user.getImageUrl());
        userRepository.save(user);
    }

    //delete
    public void deleteById(Long userId){
        userRepository.deleteById(userId);
    }
}
