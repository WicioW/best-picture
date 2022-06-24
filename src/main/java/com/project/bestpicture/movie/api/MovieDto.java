package com.project.bestpicture.movie.api;

import com.project.bestpicture.movie.domain.Movie;
import com.project.bestpicture.movie.imdb.api.ImdbFullMovieInfoDto;

public class MovieDto {
  public Long id;
  public String movieTitle;
  public String year;
  public String category;
  public Long boxOfficeInDollars;
  public String imdbID;

  public ImdbFullMovieInfoDto additionalInfo;
  public boolean wonBestPicture;
  public Integer yourRating;
  public Integer userRating;


  public MovieDto(ImdbFullMovieInfoDto imdb, Movie movie, Integer yourRating, Integer userRating){
    this.additionalInfo=imdb;
    this.id = movie.getId();
    this.movieTitle = movie.getNominee();
    this.year=movie.getYear();
    this.category=movie.getCategory();
    this.yourRating=yourRating;
    this.userRating=userRating;
    this.wonBestPicture=movie.isWonBestPicture();
    this.boxOfficeInDollars=movie.getBoxOfficeInDollars();
    this.imdbID=movie.getImdbID();
  }

  public MovieDto(ImdbFullMovieInfoDto imdb){
    this.additionalInfo=imdb;
  }

  public MovieDto(Movie movie){
    this.id = movie.getId();
    this.movieTitle = movie.getNominee();
    this.year=movie.getYear();
    this.category=movie.getCategory();
    this.wonBestPicture=movie.isWonBestPicture();
    this.boxOfficeInDollars=movie.getBoxOfficeInDollars();
    this.imdbID=movie.getImdbID();
  }

}
