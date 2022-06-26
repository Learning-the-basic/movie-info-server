package com.movieinfo.sharewatch.web;

import com.movieinfo.sharewatch.domain.movie.movieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private movieRepository movieReposit;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/post")
    public String postFront(){
        return "post_register";
    }

    @GetMapping("/login")
    public String login(){return "login_form";}
}
