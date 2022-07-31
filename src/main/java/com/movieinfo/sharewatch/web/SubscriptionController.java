package com.movieinfo.sharewatch.web;

import com.movieinfo.sharewatch.domain.posts.Posts;
import com.movieinfo.sharewatch.service.SubscriptionService;
import com.movieinfo.sharewatch.web.dto.post.PostDto;
import com.movieinfo.sharewatch.web.dto.post.PostUpdateRequest;
import com.movieinfo.sharewatch.web.dto.post.PostUpdateResponse;
import com.movieinfo.sharewatch.web.dto.post.PostsSaveRequestDto;
import com.movieinfo.sharewatch.web.dto.subscription.SubscriptionDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class SubscriptionController {

    private final SubscriptionService subService;

    //selectAll
    @ApiOperation(value = "커뮤니티 게시글 전체 조회", notes = "커뮤니티 게시글모두 조회한다.")
    @GetMapping("/api/subscription/subList")
    public void selectSubscriptionList(Model model){
        model.addAttribute("subList" , subService.selectSubscriptionList());
    }

    //selectOne
    @ApiOperation(value = "커뮤니티 게시글 상세 조회", notes = "커뮤니티 게시글을 상세 조회한다.")
    @GetMapping("api/subscription/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SubscriptionDto findSubscription(@PathVariable("id") Long post_id){

        subService.updateCount(post_id);

        return subService.findSubscription(post_id);
    }

    //insert
    @ApiOperation(value = "커뮤니티 게시글 생성", notes = "커뮤니티 게시글을 생성한다.")
    @PostMapping("/api/subscription")
    @ResponseStatus(HttpStatus.CREATED)
    public Long insertSubscription(@Valid @ModelAttribute SubscriptionDto.SubSaveRequestDto subSaveRequestDto, @Valid @ModelAttribute PostsSaveRequestDto post){
        return subService.insertSubscription(subSaveRequestDto, post);
    }

    //update
    @ApiOperation(value = "커뮤니티 게시글 수정", notes = "커뮤니티 게시글을 수정한다.")
    @PutMapping("/api/subscription/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SubscriptionDto update(
            @ApiParam(value = "게시글 id", required = true) @PathVariable Long id,
            @Valid @ModelAttribute SubscriptionDto.SubUpdateRequestDto subReq
    ) {
        subService.update(id, subReq);
        return subService.findSubscription(id);
    }

    //delete
    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제한다.")
    @DeleteMapping("/api/subscription/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@ApiParam(value = "게시글 id", required = true) @PathVariable Long id) {
        subService.delete(id);
    }

}
