package com.project.bestpicture.movie.api;

import com.project.bestpicture.movie.domain.MovieFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@Tag(name = "Movies controller")
public class RestMovieController {

  private final MovieFacade movieFacade;

  public RestMovieController(MovieFacade movieFacade) {
    this.movieFacade = movieFacade;
  }

  @GetMapping
  @Operation(summary = "Find movie by it's title")
  public ResponseEntity<MovieDto> getMovieByTitle(
      @RequestParam(name = "title", required = true) String title) {
    return ResponseEntity.ok(movieFacade.getMovieByTitle(title));
  }

  @GetMapping("/search")
  @Operation(summary = "Search for movies")
  public List<MovieDto> searchAll(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                         @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                         @RequestParam(value = "sortBy", defaultValue = "boxOfficeInDollars", required = false) String sortBy,
                         @RequestParam(value = "sortDir", defaultValue = "desc", required = false) String sortDir){
    Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
            : Sort.by(sortBy).descending();
    Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
    return movieFacade.findAll(pageable);
  }
}
