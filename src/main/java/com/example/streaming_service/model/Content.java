package com.example.streaming_service.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "content")
public class Content {

    @Id
    @Column(name = "content_id")
    private String contentId;

    @Column(name = "title")
    private String title;

    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @Column(name = "genre")
    private String genre;

    @Column(name = "imdb_link")
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
