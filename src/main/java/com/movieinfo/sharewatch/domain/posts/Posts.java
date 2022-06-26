package com.movieinfo.sharewatch.domain.posts;

import com.movieinfo.sharewatch.domain.BaseTimeEntity;
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
    private Long post_id;

    @Column(length = 255,nullable = false)
    private String title;

    private Long comm_writer_id;
    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;
    private int count;
    private Status status;

    @Builder
    public Posts( String title, Long writer_id, String content, Status status) {
        this.title = title;
        this.comm_writer_id = writer_id;
        this.content = content;
        this.count = 0;
        this.status = status;
    }
}
