package com.example.streaming_service.dto;

public class MovieStreamRequest {
    private String userId;
    private String contentId;

    //Getters
    public String getUserId()
    {
        return userId;
    }

    public String getContentId()
    {
        return contentId;
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
}

