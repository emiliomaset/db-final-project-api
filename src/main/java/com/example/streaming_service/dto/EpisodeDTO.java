package com.example.streaming_service.dto;

public class EpisodeDTO {
    private String contentId;
    private String title;
    private String duration;
    private Integer seasonNum;

    public String getContentId(){return contentId;}
    public void setContentId(){this.contentId = contentId;}

    public String getTitle(){return title;}
    public void setTitle(){this.title = title;}

    public String getDuration(){return duration;}
    public void setDuration(){this.duration = duration;}

    public Integer getSeasonNum(){return seasonNum;}
    public void setSeasonNum(){this.seasonNum = seasonNum;}
}
