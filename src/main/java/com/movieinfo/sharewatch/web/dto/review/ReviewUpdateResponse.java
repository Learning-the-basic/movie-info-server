package com.movieinfo.sharewatch.web.dto.review;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@ApiModel(value = "리뷰 수정 요청")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewUpdateResponse {
    @ApiModelProperty(value = "리뷰 내용", notes = "리뷰 내용을 입력해주세요", required = true, example = "review content")
    private Optional<String> reviewContent;

    @ApiModelProperty(value = "리뷰 별점", notes = "리뷰 별점을 입력해주세요", required = true, example = "review movieScore")
    private Optional<Double> movieScore;

    @ApiModelProperty(value = "리뷰 타입", notes = "리뷰 리뷰을 입력해주세요", required = true, example = "review refType")
    private Optional<String> reftype;

}
