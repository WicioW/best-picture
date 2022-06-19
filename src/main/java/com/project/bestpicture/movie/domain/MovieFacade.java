package com.project.bestpicture.movie.domain;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.project.bestpicture.util.ListUtil.getBatches;

@Service
public class MovieFacade {

  private final System.Logger LOGGER = System.getLogger(this.getClass().getName());

  private final MovieRepository movieRepository;

  public MovieFacade(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  @Transactional
  public void persistMovieList(List<Movie> movies) {
    if (movies.size() < 1000) {
      movieRepository.saveAll(movies);
      LOGGER.log(System.Logger.Level.INFO, "Persisted movies batch of size:" + movies.size());
    } else {
      List<List<Movie>> batches = getBatches(movies, 1000);
      for (List<Movie> batch : batches) {
        movieRepository.saveAll(batch);
        LOGGER.log(System.Logger.Level.INFO, "Persisted movies batch of size:" + batch.size());
      }
    }
  }

  public Long getNumberOfMoviesInDatabase() {
    return movieRepository.count();
  }
}
