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
@AllArgsConstructor
public class SubscriptionDto extends PostDto{

    private String subPeriod;

    private String subService;

    private int subCharge;

    @Builder
    public SubscriptionDto(Long id, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt, UserDto userDto,
                           String subPeriod, String subService, int subCharge){
        super(id, title, content, createdAt, modifiedAt, userDto);
        this.subPeriod = subPeriod;
        this.subService = subService;
        this.subCharge = subCharge;

    }

    public SubscriptionDto(Long id, String title, String content, LocalDateTime createdDate, LocalDateTime modifiedDate,
                           String subPeriod, String subService, int subCharge, UserDto toDto) {
    }



    public static SubscriptionDto toDto(Subscription sub){
       return new SubscriptionDto(
               sub.getId(),
               sub.getTitle(),
               sub.getContent(),
               sub.getCreatedDate(),
               sub.getModifiedDate(),
               sub.getSubPeriod(),
               sub.getSubService(),
               sub.getSubCharge(),
               UserDto.toDto(sub.getUser())
       );
    }

    @Getter
    @Setter
    public class SubSaveRequestDto{

            private String title;
            private String content;

            private UserDto founder;
            private String subPeriod;
            private String subService;
            private int subCharge;

        public Subscription toEntity(){

            return Subscription.PostBuilder()
                    .title(title)
                    .content(content)
                    .subService(subService)
                    .subPeriod(subPeriod)
                    .subCharge(subCharge)
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
    }

}
