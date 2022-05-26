package com.movieinfo.sharewatch.controller;

import com.movieinfo.sharewatch.repository.movieRepository;
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
}
