package com.movieinfo.sharewatch.exception.user;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserException extends RuntimeException{

    public UserException() {
        //super(message);
    }

    public UserException(String message) {
            super(message);
    }

}