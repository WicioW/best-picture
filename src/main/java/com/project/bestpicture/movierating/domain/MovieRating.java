package com.project.bestpicture.movierating.domain;

import com.project.bestpicture.configuration.BaseEntity;
import com.project.bestpicture.exception.IncorrectRatingException;
import com.project.bestpicture.movie.domain.Movie;
import com.project.bestpicture.user.domain.User;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "movie_id"})})
public class MovieRating extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="movie_id")
    private Movie movie;

    @Max(10)
    @Min(0)
    int rating;

    public MovieRating() {
    }

    public MovieRating(User user, Movie movie, int rating) {
        validateRating(rating);
        this.user = user;
        this.movie = movie;
        this.rating = rating;
    }

    public void setRating(int rating) {
        validateRating(rating);
        this.rating = rating;
    }

    private void validateRating(int rating){
        if(rating>10 || rating<0) throw new IncorrectRatingException();
    }

    public User getUser() {
        return user;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getRating() {
        return rating;
    }
}
