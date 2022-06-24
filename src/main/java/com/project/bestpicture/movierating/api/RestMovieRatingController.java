package com.project.bestpicture.movierating.api;

import com.project.bestpicture.movierating.domain.MovieRating;
import com.project.bestpicture.movierating.domain.MovieRatingWriteFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/movie-rating")
@Tag(name = "Movie rating controller")
public class RestMovieRatingController {

    private final MovieRatingWriteFacade movieRatingFacade;

    public RestMovieRatingController(MovieRatingWriteFacade movieRatingFacade) {
        this.movieRatingFacade = movieRatingFacade;
    }

    @PutMapping("/{id:[0-9][0-9]*}")
    @Operation(summary = "Update movie rating")
    public ResponseEntity<MovieRatingDto> updateMovie(@PathVariable("id") Long id, @Valid  @RequestBody UpdateMovieRatingDto dto){
        MovieRating mr = movieRatingFacade.createOrUpdateMovieRating(id, dto.rating);
        return ResponseEntity.ok(new MovieRatingDto(mr.getMovie().getId(), mr.getRating()));
    }
}
