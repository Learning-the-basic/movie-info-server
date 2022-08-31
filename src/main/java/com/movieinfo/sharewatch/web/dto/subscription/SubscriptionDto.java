package com.movieinfo.sharewatch.web.dto.subscription;

import com.movieinfo.sharewatch.domain.posts.Posts;
import com.movieinfo.sharewatch.domain.posts.Status;
import com.movieinfo.sharewatch.domain.subscription.Subscription;
import com.movieinfo.sharewatch.web.dto.post.PostDto;
import com.movieinfo.sharewatch.web.dto.user.UserDto;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SubscriptionDto extends PostDto{

    private String subPeriod;

    private String subService;

    private int subCharge;

    private int subMemLimit;

    private int subMemCount;
    private int count;
    private Status status;

    @Builder
    public SubscriptionDto(Long id, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt, UserDto userDto,
                           String subPeriod, String subService, int subCharge, int subMemLimit,int subMemCount, int count, Status status){
        super(id, title, content, createdAt, modifiedAt, userDto);
        this.subPeriod = subPeriod;
        this.subService = subService;
        this.subCharge = subCharge;
        this.subMemLimit = subMemLimit;
        this.subMemCount = subMemCount;
        this.count = count;
        this.status = status;

    }



    public static SubscriptionDto toDto(Subscription sub){
       return new SubscriptionDto(
               sub.getId(),
               sub.getTitle(),
               sub.getContent(),
               sub.getCreatedDate(),
               sub.getModifiedDate(),
               UserDto.toDto(sub.getUser()),
               sub.getSubPeriod(),
               sub.getSubService(),
               sub.getSubCharge(),
               sub.getSubMemLimit(),
               sub.getSubMemCount(),
               sub.getCount(),
               sub.getStatus()

       );
    }

    @Getter
    @Setter
    public class SubSaveRequestDto{

            private String title;
            private String content;
            private String subPeriod;
            private String subService;
            private int subCharge;
            private int subMemLimit;
            private int subMemCount;

        public Subscription toEntity(){

            return Subscription.PostBuilder()
                    .title(title)
                    .content(content)
                    .subService(subService)
                    .subPeriod(subPeriod)
                    .subCharge(subCharge)
                    .subMemLimit(subMemLimit)
                    .subMemCount(subMemCount)
                    .build();

        }

    }

    @Setter
    @Getter
    @ApiModel(value = "커뮤니티 게시글 수정 요청")
    public class SubUpdateRequestDto {

        private String title;
        private String content;
        private String subPeriod;
        private String subService;
        private int subCharge;
        private int subMemLimit;
    }

}
