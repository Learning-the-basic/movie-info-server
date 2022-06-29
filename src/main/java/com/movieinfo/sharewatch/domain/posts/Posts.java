package com.movieinfo.sharewatch.domain.posts;

import com.movieinfo.sharewatch.domain.BaseTimeEntity;
import com.movieinfo.sharewatch.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @Column(length = 255,nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;
    @Column(columnDefinition = "integer default 0")
    private int count;
    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Posts( String title, Long writer_id, String content, Status status,User user) {
        this.title = title;
        this.content = content;
        this.count = 0;
        this.status = status;
        this.user=user;
    }
}
