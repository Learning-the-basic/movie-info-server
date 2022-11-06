package com.movieinfo.sharewatch.web;

import com.movieinfo.sharewatch.domain.subscription.Subscription;
import com.movieinfo.sharewatch.domain.subscription.UserSubGroup;
import com.movieinfo.sharewatch.service.SubscriptionService;
import com.movieinfo.sharewatch.web.dto.subscription.SubscriptionDto;
import com.movieinfo.sharewatch.web.dto.subscription.UserSubGroupDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class SubscriptionController {

    private final SubscriptionService subService;

    //selectAll
    @ApiOperation(value = "커뮤니티 게시글 전체 조회", notes = "커뮤니티 게시글모두 조회한다.")
    @GetMapping("/community")
    public String selectSubscriptionList(Model model, @RequestParam(required = false, defaultValue = "0", value="page") int page){

        Page<SubscriptionDto> listPage = subService.selectSubscriptionList(page);

        int totalPage = listPage.getTotalPages();

        model.addAttribute("subscription" , listPage.getContent());
        model.addAttribute("totalPage" , totalPage);

        return "subscription/community";
    }

    //selectOne
    @ApiOperation(value = "커뮤니티 게시글 상세 조회", notes = "커뮤니티 게시글을 상세 조회한다.")
    @GetMapping("/subscription/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String selectSubscription(@PathVariable("id") Long post_id, Model model){

        model.addAttribute("subscription" , subService.selectSubscription(post_id));
        return "subscription/view";

}

    //create
    @ApiOperation(value = "커뮤니티 게시글 생성", notes = "커뮤니티 게시글을 생성한다.")
    @PostMapping("/subscription")
    @ResponseStatus(HttpStatus.CREATED)
    public String createSubscription(SubscriptionDto.SubSaveRequestDto subSaveRequestDto){
        return subService.createSubscription(subSaveRequestDto);

    }

    //update
    @ApiOperation(value = "커뮤니티 게시글 수정", notes = "커뮤니티 게시글을 수정한다.")
    @PutMapping("/subscription-update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateSubscription(
            @ApiParam(value = "게시글 id", required = true) @PathVariable Long id,
            @Valid @ModelAttribute SubscriptionDto.SubUpdateRequestDto subReq
    ) {
        subService.updateSubscription(id, subReq);
        return "subscription/community";
    }

    //delete
    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제한다.")
    @DeleteMapping("/subscription-status/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSubscription(@ApiParam(value = "게시글 id", required = true) @PathVariable("id") Long id) {
        subService.deleteSubscription(id);
    }


    @ApiOperation(value = "그룹 가입", notes = "그룹에 가입한다.")
    @PutMapping("/subscriptionGroup/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void insertSubscriptionGroupUser(@ApiParam(value = "커뮤니티 그룹 id", required = true) @PathVariable("id") Long id) {
        subService.insertSubscriptionGroupUser(id);
    }

    @ApiOperation(value = "그룹 탈퇴", notes = "그룹에서 탈퇴한다.")
    @DeleteMapping("/subscriptionGroup-exit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserAndGroup(@ApiParam(value = "커뮤니티 그룹 id", required = true) @PathVariable("id") Long id) {
         subService.deleteUserAndGroup(id);
    }


}
