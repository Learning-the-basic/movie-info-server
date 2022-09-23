package com.movieinfo.sharewatch.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsController {
/*
    private final PostsService postsService;

    //create
    @ApiOperation(value = "게시글 생성", notes = "게시글을 생성한다.")
    @PostMapping("/api/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Long save(@Valid @ModelAttribute PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }


    //read- post 한개만 찾아옴
    @ApiOperation(value = "게시글 조회", notes = "게시글을 조회한다.")
    @GetMapping("api/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto read(@PathVariable Long post_id){
        return postsService.read(post_id);
    }

    //update
    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정한다.")
    @PutMapping("/api/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostUpdateResponse update(
            @ApiParam(value = "게시글 id", required = true) @PathVariable Long id,
            @Valid @ModelAttribute PostUpdateRequest req
    ){
        return postsService.update(id,req);
    }

    //delete
    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제한다.")
    @DeleteMapping("/api/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@ApiParam(value = "게시글 id", required = true) @PathVariable Long id) {
        postsService.delete(id);
    }

 */
}
