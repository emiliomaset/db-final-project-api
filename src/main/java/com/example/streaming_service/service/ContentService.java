package com.example.streaming_service.service;

import com.example.streaming_service.model.Content;
import com.example.streaming_service.model.MovieHistory;
import com.example.streaming_service.model.User;
import com.example.streaming_service.repository.ContentRepository;
import com.example.streaming_service.repository.EpisodeHistoryRepository;
import com.example.streaming_service.repository.MovieHistoryRepository;
import com.example.streaming_service.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ContentService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContentService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private MovieHistoryRepository movieHistoryRepository;

    @Autowired
    private EpisodeHistoryRepository episodeHistoryRepository;

    public List<Content> getAllContent() {
        return contentRepository.findAll();
    }

    public List<User> getViewers(String contentId, String contentType) {
        String sql = "";
        if (contentType.equals("movie")) {
            sql = "SELECT DISTINCT name FROM user NATURAL JOIN movie_history WHERE content_id= ?";
        } else { // series
            sql = "SELECT DISTINCT name FROM user NATURAL JOIN episode_history WHERE episode_id= ?";
        }

        System.out.println(sql);

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            User user = new User();
            user.setName(rs.getString("name"));
            return user;
        }, contentId);
    }




}
