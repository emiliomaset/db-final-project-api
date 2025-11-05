package com.example.streaming_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Time;

@Entity
public class Series {

    @Id
    private String contentId;

    private short numSeasons;


    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public short getNumSeasons() {
        return numSeasons;
    }

    public void setNumSeasons(short numSeasons) {
        this.numSeasons = numSeasons;
    }
}
