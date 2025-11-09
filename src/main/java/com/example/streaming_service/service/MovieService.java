package com.example.streaming_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MovieService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //User information
    public Map<String, Object> getMemberInfo(String email)
    {
        String sql = """
        SELECT user_id, name
        FROM user
        WHERE email = ?
    """;
        return jdbcTemplate.queryForMap(sql, email);
    }

    //Movie information
    public List<Map<String, Object>> getAllMovies()
    {
        String sql = """
        SELECT content.content_id, content.title, content.release_date, content.genre, content.imdb_link
        FROM movie
        JOIN content ON movie.content_id = movie.content_id
        ORDER BY content.title;
    """;
        return jdbcTemplate.queryForList(sql);
    }

    //All sequel connections for all movies
    public List<Map<String, Object>> getSequels()
    {
        String sql = """
            SELECT movie1.title AS original_movie, movie2.title AS sequel_movie
            FROM sequel
            JOIN content movie1 ON sequel.content_id = movie1.content_id
            JOIN content movie2 ON sequel.sequel_id = movie2.content_id;
            """;
        return jdbcTemplate.queryForList(sql);
    }

    //Sequel connection for specific movie
    public List<Map<String, Object>> getMovieSequel(String contentId)
    {
        String sql = """
        SELECT movie2.title AS sequel_movie
        FROM sequel
        JOIN content movie1 ON sequel.content_id = movie1.content_id
        JOIN content movie2 ON sequel.sequel_id = movie2.content_id
        WHERE sequel.content_id = ?
    """;

        return jdbcTemplate.queryForList(sql, contentId);
    }

    //User watch history
    public List<Map<String, Object>> getMemberHistory(String userId)
    {
        String sql = """
        SELECT content.title AS watched_movie, movie_history.`timestamp` AS watch_time
        FROM movie_history
        JOIN movie ON movie_history.content_id = movie.content_id
        JOIN content ON movie.content_id = content.content_id
        WHERE movie_history.user_id = ?
    """;

        return jdbcTemplate.queryForList(sql, userId);
    }
    // ✅ Query 5: Streaming Trend in the Last 24 Hours
    public List<Map<String, Object>> getStreamingTrendLast24Hours()
    {
        String sql = """
            SELECT 
                c.title AS content_title,
                COUNT(h.stream_id) AS stream_count,
                MAX(h.timestamp) AS last_stream_time
            FROM (
                SELECT stream_id, content_id, timestamp FROM movie_history
                UNION ALL
                SELECT stream_id, content_id, timestamp FROM episode_history
            ) h
            JOIN content c ON h.content_id = c.content_id
            WHERE h.timestamp >= NOW() - INTERVAL 1 DAY
            GROUP BY c.title
            ORDER BY last_stream_time DESC;
        """;

        return jdbcTemplate.queryForList(sql);
    }

    // ✅ Query 6: Top 10 Movies/Series in the Last Month
    public List<Map<String, Object>> getTopTenLastMonth()
    {
        String sql = """
            SELECT 
                c.title AS content_title,
                COUNT(h.stream_id) AS total_streams
            FROM (
                SELECT stream_id, content_id, timestamp FROM movie_history
                UNION ALL
                SELECT stream_id, content_id, timestamp FROM episode_history
            ) h
            JOIN content c ON h.content_id = c.content_id
            WHERE h.timestamp >= NOW() - INTERVAL 1 MONTH
            GROUP BY c.title
            ORDER BY total_streams DESC
            LIMIT 10;
        """;

        return jdbcTemplate.queryForList(sql);
    }
}

