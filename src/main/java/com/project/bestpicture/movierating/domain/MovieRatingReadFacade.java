package com.project.bestpicture.movierating.domain;

import com.project.bestpicture.movie.domain.Movie;
import com.project.bestpicture.user.domain.UserFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieRatingReadFacade {

    private final System.Logger LOGGER = System.getLogger(this.getClass().getName());

    private final MovieRatingRepository movieRatingRepository;
    private final UserFacade userFacade;

    public MovieRatingReadFacade(MovieRatingRepository movieRatingRepository, UserFacade userFacade) {
        this.movieRatingRepository = movieRatingRepository;
        this.userFacade = userFacade;
    }

    public Optional<Integer> getRatingForMovieFromLoggedUser(Movie movie){
        Optional<MovieRating> byMovieAndUser = movieRatingRepository.findByMovieAndUser(movie, userFacade.getLoggedUser());
        if(byMovieAndUser.isEmpty()) return Optional.empty();
        return Optional.of(byMovieAndUser.get().getRating());
    }

    public Integer getAverageUserRatingForAMovie(Movie movie){
        Double averageRatingForAMovie = movieRatingRepository.getAverageRatingForAMovie(movie);
        if(averageRatingForAMovie==null) return null;
        return averageRatingForAMovie.intValue();
    }
}
