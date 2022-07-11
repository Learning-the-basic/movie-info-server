package com.movieinfo.sharewatch.domain.community;


import com.movieinfo.sharewatch.domain.BaseTimeEntity;
import com.movieinfo.sharewatch.domain.posts.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Community extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comm_no")
    private Long commNo;

    @Column(name = "comm_title")
    private String commTitle;

    @Column(name = "comm_content")
    private String commContent;

    @Column(name = "comm_writer")
    private String commWriter;

    @Column(name = "comm_count")
    private int commCount;

    private Status status;

}
