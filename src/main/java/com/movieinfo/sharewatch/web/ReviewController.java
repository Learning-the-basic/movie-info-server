package com.movieinfo.sharewatch.web;

import com.movieinfo.sharewatch.service.ReviewService;
import com.movieinfo.sharewatch.web.dto.review.ReviewDto;
import com.movieinfo.sharewatch.web.dto.review.ReviewSaveRequestDto;
import com.movieinfo.sharewatch.web.dto.review.ReviewUpdateResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api" )
public class ReviewController {

    private final ReviewService reviewService;

    @ApiOperation(value="리뷰 생성", notes = "리뷰를 생성한다.")
    @PostMapping("/review")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createReview(@Valid @ModelAttribute ReviewSaveRequestDto review){
        return reviewService.createReview(review);
    }

    @ApiOperation(value = "리뷰 삭제", notes = "리뷰을 삭제한다.")
    @DeleteMapping(value = "/review-status/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReview(@ApiParam(value = "리뷰  id", required = true) @PathVariable Long id) {
        reviewService.deleteReview(id);
    }

    @ApiOperation(value = "리뷰 수정", notes = "리뷰를 수정한다.")
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReviewDto updateReview(@ApiParam(value = "리뷰 id", required = true) @PathVariable Long id,
                                             @Valid @ModelAttribute ReviewUpdateResponse  reviewUpdate) {
        reviewService.updateReview(id,reviewUpdate);
        return reviewService.read(id);
    }

    @ApiOperation(value = "리뷰 조회", notes = "리뷰를 조회한다.")
    @GetMapping("/review/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReviewDto read(@ApiParam(value = "리뷰 id", required = true)@PathVariable  Long id){
        return reviewService.read(id);
    }

    @ApiOperation(value = "리뷰 전체 조회", notes = "리뷰를 전체 조회한다.")
    @GetMapping("/review")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDto> selectReviewList(Model model, @RequestParam(required = false, defaultValue = "0", value = "page")  int page){

        Page<ReviewDto> listPage =  reviewService.selectReviewList(page);

        int totalPage = listPage.getTotalPages();

        model.addAttribute("Review" , listPage.getContent());
        model.addAttribute("totalPage" , totalPage);

        return listPage.getContent();
    }

}
