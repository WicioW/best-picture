package com.project.bestpicture.movierating.api;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class UpdateMovieRatingDto {

    @Max(10)
    @Min(0)
    public int rating;
}
