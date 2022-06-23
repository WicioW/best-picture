package com.project.bestpicture.movierating.domain;

import com.project.bestpicture.configuration.BaseEntity;
import com.project.bestpicture.movie.domain.Movie;
import com.project.bestpicture.user.domain.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class MovieRating extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="movie_id")
    private Movie movie;


    int rating;

}
