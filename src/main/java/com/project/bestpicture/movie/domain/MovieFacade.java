package com.project.bestpicture.movie.domain;

import com.project.bestpicture.exception.NotFoundException;
import com.project.bestpicture.movie.api.MovieDto;
import com.project.bestpicture.movie.imdb.api.ImdbFullMovieInfoDto;
import com.project.bestpicture.movie.imdb.domain.ImdbClient;
import com.project.bestpicture.movierating.domain.MovieRatingReadFacade;
import com.project.bestpicture.movierating.domain.MovieRatingWriteFacade;
import com.project.bestpicture.util.CurrencyUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.project.bestpicture.util.ListUtil.getBatches;

@Service
public class MovieFacade {

  private final System.Logger LOGGER = System.getLogger(this.getClass().getName());

  private final MovieRepository movieRepository;
  private final ImdbClient imdbService;
  private final MovieRatingReadFacade movieRatingReadFacade;

  public MovieFacade(
      MovieRepository movieRepository,
      ImdbClient imdbService,
      MovieRatingReadFacade movieRatingReadFacade) {
    this.movieRepository = movieRepository;
    this.imdbService = imdbService;
    this.movieRatingReadFacade = movieRatingReadFacade;
  }

  public Movie getById(Long id) {
    return movieRepository.findById(id).orElseThrow(() -> new NotFoundException(Movie.class, id));
  }

  public MovieDto getMovieByTitle(String title) {
    Optional<ImdbFullMovieInfoDto> optionalImdbMovie = imdbService.getMovieByTitle(title);
    Optional<Movie> optionalMovie = movieRepository.findByNominee(title);

    if (optionalMovie.isEmpty() && optionalImdbMovie.isEmpty()) {
      throw new NotFoundException(Movie.class, title);
    }

    if (optionalMovie.isEmpty()) {
      return new MovieDto(optionalImdbMovie.orElse(null));
    } else {
      Movie movie = optionalMovie.get();
      Optional<Integer> optionalLoggedUserRating =
          movieRatingReadFacade.getRatingForMovieFromLoggedUser(movie);
      return new MovieDto(
          optionalImdbMovie.orElse(null), optionalMovie.orElse(null),
          optionalLoggedUserRating.orElse(null),
              movieRatingReadFacade.getAverageUserRatingForAMovie(movie));
    }
  }

  public List<Movie> getAllMovies() {
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

  @Transactional
  public void updateMovieFromImdb(Movie movie, ImdbFullMovieInfoDto imdbInfo) {
    movie.setImdbID(imdbInfo.imdbID);
    movie.setBoxOfficeInDollars(CurrencyUtil.currencyToLong(imdbInfo.Boxoffice));
    movieRepository.save(movie);
  }

  public List<MovieDto> findAll(Pageable pageable) {
    Page<Movie> all = movieRepository.findAll(pageable);
    return all.getContent().stream().map(MovieDto::new).collect(Collectors.toList());
  }
}
