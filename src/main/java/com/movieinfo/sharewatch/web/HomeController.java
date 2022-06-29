package com.movieinfo.sharewatch.web;

import com.movieinfo.sharewatch.config.auth.dto.SessionUser;
import com.movieinfo.sharewatch.domain.movie.movieRepository;
import com.movieinfo.sharewatch.web.dto.PostsSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private movieRepository movieRepository;
    @Autowired
    private HttpSession httpSession;

    @GetMapping("/posts/save")
    public String createForm(Model model, PostsSaveRequestDto requestDto){
        model.addAttribute("requestDto",requestDto);
        return "post_save";
    }

    @GetMapping("/")
    public String login(Model model){

        SessionUser user =(SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("username", user.getName());
            model.addAttribute("image",user.getPicture());
        }

        return "login_form";}
}
