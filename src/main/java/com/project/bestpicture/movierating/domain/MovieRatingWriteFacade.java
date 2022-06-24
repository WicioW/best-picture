package com.project.bestpicture.movierating.domain;

import com.project.bestpicture.movie.domain.Movie;
import com.project.bestpicture.movie.domain.MovieFacade;
import com.project.bestpicture.user.domain.User;
import com.project.bestpicture.user.domain.UserFacade;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class MovieRatingWriteFacade {

    private final System.Logger LOGGER = System.getLogger(this.getClass().getName());

    private final MovieRatingRepository movieRatingRepository;
    private final UserFacade userFacade;
    private final MovieFacade movieFacade;

    public MovieRatingWriteFacade(MovieRatingRepository movieRatingRepository, UserFacade userFacade, MovieFacade movieFacade) {
        this.movieRatingRepository = movieRatingRepository;
        this.userFacade = userFacade;
        this.movieFacade = movieFacade;
    }

    @Transactional
    public MovieRating createOrUpdateMovieRating(Long movieId, int rating){
        User user = userFacade.getLoggedUser();
        Movie movie = movieFacade.getById(movieId);

        Optional<MovieRating> optionalMovieRating = movieRatingRepository.findByMovieAndUser(movie, user);
        MovieRating movieRating;
        if(optionalMovieRating.isEmpty()) {
            movieRating = new MovieRating(user, movie, rating);
        }else{
            movieRating = optionalMovieRating.get();
            movieRating.setRating(rating);
        }
        return movieRatingRepository.saveAndFlush(movieRating);
    }

}
