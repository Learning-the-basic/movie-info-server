package com.movieinfo.sharewatch.domain.movie;

import com.movieinfo.sharewatch.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name="movie")
public class Movie extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="movie_id")
    private Long id; //영화번호

    private String title;
    private String image;
    private String subtitle;
    private LocalDateTime pubDate;
    private String director;
    private String actor;
    private int userRating;

    @Builder
    public Movie(Long id, String title, String image, String subtitle, LocalDateTime pubDate, String director, String actor, int userRating) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.subtitle = subtitle;
        this.pubDate = pubDate;
        this.director = director;
        this.actor = actor;
        this.userRating = userRating;
    }

}
