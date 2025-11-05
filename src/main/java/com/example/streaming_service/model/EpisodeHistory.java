package com.example.streaming_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Time;

@Entity
public class EpisodeHistory {

    @Id
    private String streamId;

    private Time timestamp;
    private String userId;
    private String contentId;
    private short seasonNum;
    private String episodeId;

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    public Time getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Time timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public short getSeasonNum() {
        return seasonNum;
    }

    public void setSeasonNum(short seasonNum) {
        this.seasonNum = seasonNum;
    }

    public String getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(String episode_id) {
        this.episodeId = episode_id;
    }
}
