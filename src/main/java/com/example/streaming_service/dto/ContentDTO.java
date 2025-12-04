package com.example.streaming_service.dto;

import com.example.streaming_service.config.CustomJSONDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class ContentDTO {
    private String title;
    private String releaseDate;
    private String genre;
    private String imdbLink;

    private String type; // "movie" or "series"

    private String duration; // for movie (HH:MM:SS)
    private Integer numSeasons; // for series

    public String getTitle(){return title;}
    public void setTitle(){this.title = title;}

    public String getReleaseDate(){return releaseDate;}
    public void setReleaseDate(){this.releaseDate = releaseDate;}

    public String getGenre(){return genre;}
    public void setGenre(){this.genre = genre;}

    public String getImdbLink(){return imdbLink;}
    public void setImdbLink(){this.imdbLink = imdbLink;}

    public String getType(){return type;}
    public void setType(){this.type = type;}

    public String getDuration(){return duration;}
    public void set(){this.duration = duration;}

    public Integer getNumSeasons(){return numSeasons;}
    public void setNumSeasons(){this.numSeasons = numSeasons;}
}
