package com.example.streaming_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.sql.Date;

@Entity
public class Content {

    @Id
    private String contentId;

    private String title;
    private Date  releaseDate;
    private String genre;
    private String imdbLink;

    public String getContentId() {
        return contentId;
    }

    public String getTitle() {
        return title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public String getImdbLink() {
        return imdbLink;
    }


    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setImdbLink(String imdbLink) {
        this.imdbLink = imdbLink;
    }
}
