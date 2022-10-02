package com.movieinfo.sharewatch.web.dto.subscription;


import com.movieinfo.sharewatch.domain.posts.Status;
import com.movieinfo.sharewatch.domain.subscription.Subscription;
import com.movieinfo.sharewatch.web.dto.user.UserDto;
import io.swagger.annotations.ApiModel;
import lombok.*;


import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class SubscriptionDto{

    private Long id;

    private String title;

    private String content;

    private Integer count;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private UserDto userDto;
    //private UserSubGroupDto userSubGroupDto;
    private String subService;
    private int subCharge;
    private int subMemLimit;
    private int subMemCount;

    private SubScriptionGroupDto subGroupDto;
    private Status status;


    @Builder
    public SubscriptionDto(Long id, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt, UserDto userDto,/*UserSubGroupDto userSubGroupDto,*/
                           String subService, int subCharge, int subMemLimit,int subMemCount, SubScriptionGroupDto subGroupDto, int count, Status status){
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.userDto = userDto;
        //this.userSubGroupDto = userSubGroupDto;
        this.subService = subService;
        this.subCharge = subCharge;
        this.subMemLimit = subMemLimit;
        this.subMemCount = subMemCount;
        this.subGroupDto = subGroupDto;
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
               //UserSubGroupDto.toDto(sub.get)
               sub.getSubService(),
               sub.getSubCharge(),
               sub.getSubMemLimit(),
               sub.getSubMemCount(),
               SubScriptionGroupDto.toDto(sub.getSubGroup()),
               sub.getCount(),
               sub.getStatus()

       );
    }

    @Getter
    @Setter
    public static class SubSaveRequestDto{

            private String title;
            private String content;
            private String subService;
            private int subCharge;
            private int subMemLimit;

        public Subscription toEntity(){

            return Subscription.PostBuilder()
                    .title(title)
                    .content(content)
                    .subService(subService)
                    .subCharge(subCharge)
                    .subMemLimit(subMemLimit)
                    .build();

        }

    }

    @Setter
    @Getter
    @ApiModel(value = "커뮤니티 게시글 수정 요청")
    public static class SubUpdateRequestDto {

        private String title;
        private String content;
        private String subService;
        private int subCharge;
        private int subMemLimit;
    }

}
