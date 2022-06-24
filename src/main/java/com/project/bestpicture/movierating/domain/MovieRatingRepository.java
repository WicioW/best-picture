package com.project.bestpicture.movierating.domain;

import com.project.bestpicture.movie.domain.Movie;
import com.project.bestpicture.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRatingRepository extends JpaRepository<MovieRating, Long> {


    Optional<MovieRating> findByMovieAndUser(Movie movie, User user);

    @Query("select avg(rating) from MovieRating where movie=?1")
    Double getAverageRatingForAMovie(Movie movie);


}
