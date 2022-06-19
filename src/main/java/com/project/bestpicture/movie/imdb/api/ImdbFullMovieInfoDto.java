package com.project.bestpicture.movie.imdb.api;

import com.project.bestpicture.movie.api.RatingsItem;

import java.util.List;

public class ImdbFullMovieInfoDto {
	private String metascore;
	private String boxOffice;
	private String website;
	private String imdbRating;
	private String imdbVotes;
	private List<RatingsItem> ratings;
	private String runtime;
	private String language;
	private String rated;
	private String production;
	private String released;
	private String imdbID;
	private String plot;
	private String director;
	private String title;
	private String actors;
	private String response;
	private String type;
	private String awards;
	private String dVD;
	private String year;
	private String poster;
	private String country;
	private String genre;
	private String writer;

	public String getMetascore(){
		return metascore;
	}

	public String getBoxOffice(){
		return boxOffice;
	}

	public String getWebsite(){
		return website;
	}

	public String getImdbRating(){
		return imdbRating;
	}

	public String getImdbVotes(){
		return imdbVotes;
	}

	public List<RatingsItem> getRatings(){
		return ratings;
	}

	public String getRuntime(){
		return runtime;
	}

	public String getLanguage(){
		return language;
	}

	public String getRated(){
		return rated;
	}

	public String getProduction(){
		return production;
	}

	public String getReleased(){
		return released;
	}

	public String getImdbID(){
		return imdbID;
	}

	public String getPlot(){
		return plot;
	}

	public String getDirector(){
		return director;
	}

	public String getTitle(){
		return title;
	}

	public String getActors(){
		return actors;
	}

	public String getResponse(){
		return response;
	}

	public String getType(){
		return type;
	}

	public String getAwards(){
		return awards;
	}

	public String getDVD(){
		return dVD;
	}

	public String getYear(){
		return year;
	}

	public String getPoster(){
		return poster;
	}

	public String getCountry(){
		return country;
	}

	public String getGenre(){
		return genre;
	}

	public String getWriter(){
		return writer;
	}
}