package com.movieinfo.sharewatch.web.dto.review;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "리뷰 수정 요청")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewUpdateResponse {

    private Long id;
    private String reviewContent;
    private Double movieScore;
    private String refType;

}
