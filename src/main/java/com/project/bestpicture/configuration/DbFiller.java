package com.project.bestpicture.configuration;

import com.project.bestpicture.movie.domain.Movie;
import com.project.bestpicture.movie.domain.MovieFacade;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class DbFiller implements ApplicationRunner {

  private static final List<String> CATEGORIES_TO_IMPORT_FROM_CSV = List.of("Best Picture");
  private final System.Logger LOGGER = System.getLogger(this.getClass().getName());

  private final MovieFacade movieFacade;

  public DbFiller(MovieFacade movieFacade) {
    this.movieFacade = movieFacade;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    LOGGER.log(System.Logger.Level.INFO, "---=== DBFILLER STARTED ===---");
    importMovieDataFromCSVFile();
    LOGGER.log(System.Logger.Level.INFO, "---=== DBFILLER FINISHED ===---");
  }

  public static String readFileAsString(String fileName) throws Exception {
    return new String(Files.readAllBytes(Paths.get(fileName)));
  }

  private void importMovieDataFromCSVFile() {
    if(movieFacade.getNumberOfMoviesInDatabase()!=0) return;
    String data = "";
    try {
      data = readFileAsString("config/academy_awards.csv");
    } catch (Exception e) {
      //
    }

    List<Movie> moviesListFromFile = importBestPictureMoviesListFromFile(data);
    movieFacade.persistMovieList(moviesListFromFile);
  }

  private List<Movie> importBestPictureMoviesListFromFile(String data) {
    List<Movie> moviesFromCSV = new ArrayList<>();
    data.lines()
        .skip(1)
        .forEach(
            line -> {
              String[] columns = line.split(",");
              String category = columns[1];
              if (CATEGORIES_TO_IMPORT_FROM_CSV.contains(category)) {
                String wonBestMovie = columns[4];
                Movie movie =
                    new Movie(columns[0], category, columns[2], "YES".equals(wonBestMovie));
                moviesFromCSV.add(movie);
                LOGGER.log(System.Logger.Level.INFO, "Imported movie:" + movie.getNominee());
              }
            });
    return moviesFromCSV;
  }
}
