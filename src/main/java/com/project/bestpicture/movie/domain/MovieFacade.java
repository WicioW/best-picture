package com.project.bestpicture.movie.domain;

import com.project.bestpicture.movie.api.MovieDto;
import com.project.bestpicture.movie.imdb.api.ImdbFullMovieInfoDto;
import com.project.bestpicture.movie.imdb.domain.ImdbClient;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.project.bestpicture.util.ListUtil.getBatches;

@Service
public class MovieFacade {

  private final System.Logger LOGGER = System.getLogger(this.getClass().getName());

  private final MovieRepository movieRepository;
  private final ImdbClient imdbService;

  public MovieFacade(MovieRepository movieRepository, ImdbClient imdbService) {
    this.movieRepository = movieRepository;
    this.imdbService = imdbService;
  }

  public MovieDto getMovieByTitle(String title){
    ImdbFullMovieInfoDto imdbMovieDto = imdbService.getMovieByTitle(title);
    Optional<Movie> byNominee = movieRepository.findByNominee(title);
    MovieDto dto = new MovieDto(title, imdbMovieDto,byNominee.isPresent()? byNominee.get().isWonBestPicture(): false);
    return dto;
  }

  public List<Movie> getAllMovies(){
    return movieRepository.findAll();
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
