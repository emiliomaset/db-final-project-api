package com.example.streaming_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Time;

@Entity
public class Movie {

    @Id
    private String contentId;

    private Time duration;

    public String getContentId() {
        return contentId;
    }

    public Time getDuration() {
        return duration;
    }


    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }
}
