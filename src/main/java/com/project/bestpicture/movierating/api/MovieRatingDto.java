package com.project.bestpicture.movierating.api;

public class MovieRatingDto {

    public Long movieId;
    public int rating;

    public MovieRatingDto(Long movieId, int rating) {
        this.movieId = movieId;
        this.rating = rating;
    }
}
