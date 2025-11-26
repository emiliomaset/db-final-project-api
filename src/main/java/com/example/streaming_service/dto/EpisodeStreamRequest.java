package com.example.streaming_service.dto;

public class EpisodeStreamRequest
{
    private String userId;
    private String contentId;
    private String episodeId;
    private int seasonNum;

    //Getters
    public String getUserId()
    {
        return userId;
    }

    public String getContentId()
    {
        return contentId;
    }
    public String getEpisodeId()
    {
        return episodeId;
    }
    public int getSeasonNum()
    {
        return seasonNum;
    }

    //Setters
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    public void setContentId(String contentId)
    {
        this.contentId = contentId;
    }
    public void setEpisodeId(String episodeId)
    {
        this.episodeId = episodeId;
    }
    public void setSeasonNum(int seasonNum)
    {
        this.seasonNum = seasonNum;
    }
}

