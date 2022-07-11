package com.movieinfo.sharewatch.web.dto;

import com.movieinfo.sharewatch.domain.community.Community;
import com.movieinfo.sharewatch.domain.posts.Status;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class CommunityDto {

    private long commNo;

    private String commTitle;

    private String commContent;

    private String commWriter;

    private int commCount;

    private LocalDateTime createDate;

    private Status status;

    public Community toEntity(){

        Community community = Community.builder()
                .commTitle(commTitle)
                .commContent(commContent)
                .commWriter(commWriter)
                .commCount(commCount)
                .status(Status.Y)
                .build();
        return community;

    }

}
