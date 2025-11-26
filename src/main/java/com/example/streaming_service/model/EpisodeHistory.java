package com.example.streaming_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Timestamp;

@Entity
public class EpisodeHistory {

    @Id
    @Column(name = "stream_id")
    private String streamId;

    @Column(name = "episode_watch_time")
    private Timestamp timestamp;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "content_id")
    private String contentId;

    @Column(name = "season_num")
    private short seasonNum;

    @Column(name = "episode_id")
    private String episodeId;

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
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
