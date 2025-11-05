package com.example.streaming_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Time;

@Entity
public class Episode {

    @Id
    private String episodeId;

    private String title;
    private Time duration;
    private short seasonNum;
    private String contentId;

    public String getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(String episodeId) {
        this.episodeId = episodeId;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public short getSeasonNum() {
        return seasonNum;
    }

    public void setSeasonNum(short seasonNum) {
        this.seasonNum = seasonNum;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }
}
