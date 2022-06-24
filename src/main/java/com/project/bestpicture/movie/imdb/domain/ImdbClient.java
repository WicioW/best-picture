package com.project.bestpicture.movie.imdb.domain;

import com.project.bestpicture.movie.imdb.api.ImdbFullMovieInfoDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ImdbClient {

  @Value("${imdb.api.key}")
  private String IMDB_API_KEY;

  private static final String IMDB_API_URI = "https://omdbapi.com?";

  @CircuitBreaker(name = "imdbService", fallbackMethod = "getFallback")
  public Optional<ImdbFullMovieInfoDto> getMovieByTitle(String title) {
    String uri =
        new StringBuilder(IMDB_API_URI)
            .append("apikey=")
            .append(IMDB_API_KEY)
            .append("&t=")
            .append(title)
            .toString();
    RestTemplate restTemplate = new RestTemplate();
    ImdbFullMovieInfoDto dto = restTemplate.getForObject(uri, ImdbFullMovieInfoDto.class);

    if (dto == null || "False".equals(dto.Response)) {
      return Optional.empty();
    }
    return Optional.of(dto);
  }

  private Optional<ImdbFullMovieInfoDto> getFallback(String title, Exception e) {
    return Optional.of(new ImdbFullMovieInfoDto());
  }
}
