package com.project.bestpicture.movie.api;

import com.project.bestpicture.movie.domain.MovieFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
@Tag(name = "Movies controller")
public class RestMovieController {

  private final MovieFacade movieFacade;

  public RestMovieController(MovieFacade movieFacade) {
    this.movieFacade = movieFacade;
  }

  @GetMapping("/search")
  @Operation(summary = "Find movie by it's title")
  public ResponseEntity<MovieDto> getMovieByTitle(@RequestParam(name = "title", required = true) String title) {
    return ResponseEntity.ok(movieFacade.getMovieByTitle(title));
  }
}
