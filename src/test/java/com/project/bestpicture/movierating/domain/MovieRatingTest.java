package com.project.bestpicture.movierating.domain;

import com.project.bestpicture.exception.IncorrectRatingException;
import com.project.bestpicture.movie.domain.Movie;
import com.project.bestpicture.user.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieRatingTest {

    @Test
    void ratingZeroShouldBeAccepted(){
       assertDoesNotThrow(()-> {
           new MovieRating(new User(), new Movie(), 0);
       });
    }

    @Test
    void ratingFiveShouldBeAccepted(){
        assertDoesNotThrow(()-> {
            new MovieRating(new User(), new Movie(), 5);
        });
    }
    @Test
    void ratingTenShouldBeAccepted(){
        assertDoesNotThrow(()-> {
            new MovieRating(new User(), new Movie(), 10);
        });
    }

    @Test
    void ratingElevenShouldThrowIncorrectRatingException(){
        assertThrows(IncorrectRatingException.class, ()-> {
            new MovieRating(new User(), new Movie(), 11);
        });
    }

    @Test
    void ratingMinusOneShouldThrowIncorrectRatingException(){
        assertThrows(IncorrectRatingException.class, ()-> {
            new MovieRating(new User(), new Movie(), -1);
        });
    }

}