package com.movieinfo.sharewatch.domain.movie;

import com.movieinfo.sharewatch.domain.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface movieRepository extends JpaRepository<Movie,Long> {
}
