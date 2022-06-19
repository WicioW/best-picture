package com.project.bestpicture.movie.domain;

import com.project.bestpicture.configuration.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Movie extends BaseEntity {

    private String year;
    private String category;

    @Column(length = 500)
    private String nominee;
    private boolean wonBestPicture;

    public Movie() {
    }

    public Movie(String year, String category, String nominee, boolean wonBestPicture) {
        this.year = year;
        this.category = category;
        this.nominee = nominee;
        this.wonBestPicture = wonBestPicture;
    }

    public String getYear() {
        return year;
    }

    public String getCategory() {
        return category;
    }

    public String getNominee() {
        return nominee;
    }

    public boolean isWonBestPicture() {
        return wonBestPicture;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "year='" + year + '\'' +
                ", category='" + category + '\'' +
                ", nominee='" + nominee + '\'' +
                ", wonBestPicture=" + wonBestPicture +
                '}';
    }
}
