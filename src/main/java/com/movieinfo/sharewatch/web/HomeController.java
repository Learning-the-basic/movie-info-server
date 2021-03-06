package com.movieinfo.sharewatch.web;

import com.movieinfo.sharewatch.domain.movie.movieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class HomeController {

    @Autowired
    private movieRepository movieRepository;

/* thymeleaf로 테스트 해봄
    @GetMapping("/posts/save")
    public String createForm(Model model, PostsSaveRequestDto requestDto){
        model.addAttribute("requestDto",requestDto);
        return "post_save";
    }
*/


}
