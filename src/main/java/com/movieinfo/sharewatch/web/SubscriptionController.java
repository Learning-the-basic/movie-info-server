package com.movieinfo.sharewatch.web;

import com.movieinfo.sharewatch.domain.subscription.Subscription;
import com.movieinfo.sharewatch.domain.user.User;
import com.movieinfo.sharewatch.service.SubscriptionService;
import com.movieinfo.sharewatch.web.dto.post.PostsSaveRequestDto;
import com.movieinfo.sharewatch.web.dto.subscription.SubscriptionDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class SubscriptionController {

    private final SubscriptionService subService;

    /*
    //selectAll
    @ApiOperation(value = "커뮤니티 게시글 전체 조회", notes = "커뮤니티 게시글모두 조회한다.")
    @GetMapping("/api/subscription")
    public int selectSubscriptionList(Model model){

        List<SubscriptionDto> list = subService.selectSubscriptionList();

        model.addAttribute("subList" , subService.selectSubscriptionList());

        return 1;
    }
*/

    //selectAll
    @ApiOperation(value = "커뮤니티 게시글 전체 조회", notes = "커뮤니티 게시글모두 조회한다.")
    @GetMapping("/api/community")
    public int selectSubscriptionList(Model model, @RequestParam(required = false, defaultValue = "0", value="page") int page){

        Page<SubscriptionDto> listPage = subService.selectSubscriptionList(page);

        int totalPage = listPage.getTotalPages();

        model.addAttribute("subscription" , listPage.getContent());
        model.addAttribute("totalPage" , totalPage);

        return totalPage;
    }

    //selectOne
    @ApiOperation(value = "커뮤니티 게시글 상세 조회", notes = "커뮤니티 게시글을 상세 조회한다.")
    @GetMapping("api/subscriptions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String selectSubscription(@PathVariable("id") Long post_id){
        return subService.selectSubscription(post_id).toString();
    }

    //create
    @ApiOperation(value = "커뮤니티 게시글 생성", notes = "커뮤니티 게시글을 생성한다.")
    @PostMapping("/api/subscription")
    @ResponseStatus(HttpStatus.CREATED)
    public String createSubscription(SubscriptionDto.SubSaveRequestDto subSaveRequestDto){
        return subService.createSubscription(subSaveRequestDto);
    }

    //update
    @ApiOperation(value = "커뮤니티 게시글 수정", notes = "커뮤니티 게시글을 수정한다.")
    @PutMapping("/api/subscription/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateSubscription(
            @ApiParam(value = "게시글 id", required = true) @PathVariable Long id,
            @Valid @ModelAttribute SubscriptionDto.SubUpdateRequestDto subReq
    ) {
        subService.updateSubscription(id, subReq);
        return subService.selectSubscription(id);
    }

    //delete
    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제한다.")
    @DeleteMapping("/api/subscription-status/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSubscription(@ApiParam(value = "게시글 id", required = true) @PathVariable Long id) {
        subService.deleteSubscription(id);
    }

    @ApiOperation(value = "그룹 맴버 조회", notes = "그룹을 조회한다")
    @DeleteMapping("/api/subscriptionGroup/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<User> selectSubscriptionGroup(@ApiParam(value = "그룹 id", required = true) @PathVariable Long id) {
        return subService.selectSubscriptionGroup(id);
    }

}
