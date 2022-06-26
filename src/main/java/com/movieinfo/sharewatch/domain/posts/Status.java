package com.movieinfo.sharewatch.domain.posts;

import lombok.Getter;

@Getter
public enum Status {

    Y("y"),
    N("n");

    private String status;

    Status(String status){
        this.status=status;
    }

}
