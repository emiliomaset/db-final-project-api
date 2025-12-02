package com.example.streaming_service.service;

import com.example.streaming_service.model.Content;
import com.example.streaming_service.model.MovieHistory;
import com.example.streaming_service.model.User;
import com.example.streaming_service.repository.ContentRepository;
import com.example.streaming_service.repository.EpisodeHistoryRepository;
import com.example.streaming_service.repository.MovieHistoryRepository;
import com.example.streaming_service.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
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
        return contentRepository.findAll(Sort.by("title"));
    }

    public List<HashMap> getViewers(String contentId, String contentType) {
        String sql = "";
        if (contentType.equals("movie")) {
            sql = "SELECT DISTINCT name, COUNT(user_id) AS countViews, MIN(movie_watch_time) AS lastView" +
                    " FROM user NATURAL JOIN movie_history" +
                    " WHERE content_id= ?" +
                    "GROUP BY user_id";
        } else { // series
            sql = "SELECT DISTINCT name, COUNT(user_id) AS countViews, MIN(episode_watch_time) AS lastView" +
                    " FROM user NATURAL JOIN episode_history" +
                    " WHERE episode_id= ?" +
                    "GROUP BY user_id";
        }

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            final HashMap<String, String> viewer = new HashMap<>();
            viewer.put("name", rs.getString("name"));
            viewer.put("timesViewed", rs.getString("countViews"));
            viewer.put("lastView", rs.getString("lastView"));
            return viewer;
        }, contentId);
    }
}