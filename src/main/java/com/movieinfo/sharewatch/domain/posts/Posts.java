package com.movieinfo.sharewatch.domain.posts;

import com.movieinfo.sharewatch.domain.BaseTimeEntity;
import com.movieinfo.sharewatch.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
//@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn // 하위 테이블의 구분 컬럼 생성(default = DTYPE)
@MappedSuperclass
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 255,nullable = false, name = "post_title")
    protected String title;

    @Column(columnDefinition = "TEXT",nullable = false, name = "post_content")
    protected String content;

    @Column(columnDefinition = "integer default 0", name = "post_count")
    private int count;

    @Enumerated(EnumType.STRING)
    //@Column(columnDefinition = "default 'N'")
    private Status status;

    @Builder
    public Posts(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void updateTitle(String title) { this.title=title; }
    public void updateContent(String content) {this.content = content; }

    public void increaseCount() {
        this.count++;
    }

    /**
     * 게시글 삭제
     */
    public void delete() {
        this.status = Status.N;
    }

}
