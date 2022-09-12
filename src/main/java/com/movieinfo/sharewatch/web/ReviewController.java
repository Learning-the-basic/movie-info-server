package com.movieinfo.sharewatch.web;

import com.movieinfo.sharewatch.domain.user.UserRepository;
import com.movieinfo.sharewatch.service.ReviewService;
import com.movieinfo.sharewatch.web.dto.review.ReivewUpdateResponse;
import com.movieinfo.sharewatch.web.dto.review.ReviewDto;
import com.movieinfo.sharewatch.web.dto.review.ReviewSaveRequestDto;
import com.movieinfo.sharewatch.web.dto.review.ReviewUpdateResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController //Controller와 ResponseBody의 조합
//단순 객체만을 반환하고 객체 데이터는 JSON 또는 XML 형식으로 HTTP응답에 담아서 전송
@RequiredArgsConstructor
@RequestMapping("/api" )
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    private UserRepository userRepository;

    //create
    @ApiOperation(value="리뷰 생성", notes = "리뷰를 생성한다.")
    @PostMapping("/review")
    @ResponseStatus(HttpStatus.CREATED)
    //@Valid를 적어주면 유효성 검증이 진행 된다.
    public Long saveReview(@Valid @ModelAttribute ReviewSaveRequestDto review){
        return reviewService.saveReview(review);
    }

    //리뷰 삭제
    @ApiOperation(value = "리뷰 삭제", notes = "리뷰을 삭제한다.")
    @DeleteMapping(value = "/review-status/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReview(@ApiParam(value = "리뷰  id", required = true) @PathVariable Long id) {
        reviewService.deleteReview(id);
    }

    //리뷰 수정
    @ApiOperation(value = "리뷰 수정", notes = "리뷰를 수정한다.")
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReivewUpdateResponse updateReview(@ApiParam(value = "리뷰 id", required = true) @PathVariable Long id,
                                                                  @Valid @ModelAttribute ReviewUpdateResponse  reviewUpdate){
        return reviewService.updateReview(id,reviewUpdate);
    }

    //리뷰한개 조회
    @ApiOperation(value = "리뷰 조회", notes = "리뷰를 조회한다.")
    @GetMapping("/review/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReviewDto read(@ApiParam(value = "리뷰 id", required = true)@PathVariable  Long id){
        return reviewService.read(id);
    }

    //리뷰 여러개 조회
    @ApiOperation(value = "리뷰 전체 조회", notes = "리뷰를 전체 조회한다.")
    @GetMapping("/review")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDto> selectReviewList(Model model, @RequestParam(required = false, defaultValue = "0", value = "page")  int page){

        Page<ReviewDto> listPage =  reviewService.selectReviewList(page);;

        int totalPage = listPage.getTotalPages();

        model.addAttribute("Review" , listPage.getContent());
        model.addAttribute("totalPage" , totalPage);

        return listPage.getContent();
    }

    //리뷰 리스트 조회(개인 것)
   /* @ApiOperation(value = "내가 작성한 리뷰조회", notes = "리뷰를 조회한다.")
    @GetMapping("/review-m/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String read(@ApiParam(value = "리뷰 영화", required = true)@PathVariable  String movieMn, Model model){
        List<ReviewDto> list =  reviewService.selectReviewAllMy(movieMn);
        model.addAttribute("reviewList", reviewService.selectReviewAllMy(movieMn));
        return list.toString();
    }*/
}
