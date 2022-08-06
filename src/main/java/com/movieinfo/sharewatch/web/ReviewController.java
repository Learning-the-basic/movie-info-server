package com.movieinfo.sharewatch.web;

import com.movieinfo.sharewatch.service.ReviewService;
import com.movieinfo.sharewatch.web.dto.post.PostDto;
import com.movieinfo.sharewatch.web.dto.post.PostUpdateRequest;
import com.movieinfo.sharewatch.web.dto.post.PostUpdateResponse;
import com.movieinfo.sharewatch.web.dto.review.ReivewUpdateResponse;
import com.movieinfo.sharewatch.web.dto.review.ReviewDto;
import com.movieinfo.sharewatch.web.dto.review.ReviewSaveRequestDto;
import com.movieinfo.sharewatch.web.dto.review.ReviewUpdateResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController //Controller와 ResponseBody의 조합
//단순 객체만을 반환하고 객체 데이터는 JSON 또는 XML 형식으로 HTTP응답에 담아서 전송
@RequiredArgsConstructor
@RequestMapping("/api/review" )
public class ReviewController {

    private final ReviewService reviewService;

    //create
    @ApiOperation(value="리뷰 생성", notes = "리뷰를 생성한다.")
    @PostMapping("/createReview")
    @ResponseStatus(HttpStatus.CREATED)
    //@Valid를 적어주면 유효성 검증이 진행 된다.
    public Long saveReview(@Valid @ModelAttribute ReviewSaveRequestDto review){
        //if (review.getReviewType() == 1){

        //}
        return reviewService.saveReview(review);
    }

    //리뷰 삭제
    @ApiOperation(value = "리뷰 삭제", notes = "리뷰을 삭제한다.")
    @GetMapping(value = "/deleteReview/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReview(@ApiParam(value = "리뷰  id", required = true) @PathVariable Long id) {
        reviewService.deleteReview(id);
    }

    //리뷰 수정
    @ApiOperation(value = "리뷰 수정", notes = "리뷰를 수정한다.")
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReivewUpdateResponse updateReview(@ApiParam(value = "리뷰 id", required = true) @PathVariable Long id,
                                                                  @Valid @ModelAttribute ReviewUpdateResponse  reviewUpdateRequest){
        return reviewService.updateReview(id,reviewUpdateRequest);
    }

    //게시글 한개 조회
    @ApiOperation(value = "리뷰 조회", notes = "리뷰를 조회한다.")
    @GetMapping("/selectOne/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReviewDto read(@ApiParam(value = "게시글 id", required = true)@PathVariable  Long id){
        return reviewService.read(id);
    }
}
