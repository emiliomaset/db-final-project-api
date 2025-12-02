package com.example.streaming_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID; //Unique User Identifier for Streaming
import java.util.List;
import java.util.Map;

@Service
public class MovieService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // User information
    public Map<String, Object> getMemberInfo(String email) {
        String sql = """
            SELECT user_id, name
            FROM user
            WHERE email = ?
        """;
        return jdbcTemplate.queryForMap(sql, email);
    }

    // Movie information
    public List<Map<String, Object>> getAllMovies() {
        String sql = """
            SELECT content.content_id, content.title, content.release_date, content.genre, content.imdb_link
            FROM movie
            JOIN content ON movie.content_id = content.content_id
            ORDER BY content.title;
        """;
        return jdbcTemplate.queryForList(sql);
    }

    // All sequel connections for all movies
    public List<Map<String, Object>> getSequels() {
        String sql = """
            SELECT movie1.title AS original_movie, movie2.title AS sequel_movie
            FROM sequel
            JOIN content movie1 ON sequel.content_id = movie1.content_id
            JOIN content movie2 ON sequel.sequel_id = movie2.content_id;
        """;
        return jdbcTemplate.queryForList(sql);
    }

    // Sequel connection for a specific movie
    public List<Map<String, Object>> getMovieSequel(String contentId) {
        String sql = """
            SELECT movie2.title AS sequel_movie
            FROM sequel
            JOIN content movie1 ON sequel.content_id = movie1.content_id
            JOIN content movie2 ON sequel.sequel_id = movie2.content_id
            WHERE sequel.content_id = ?
        """;
        return jdbcTemplate.queryForList(sql, contentId);
    }

    // ✅ User watch history (updated for new schema)
    public List<Map<String, Object>> getMemberHistory(String userId) {
        String sql = """
            SELECT 
                c.title AS watched_movie, 
                h.movie_watch_time AS watch_time
            FROM movie_history h
            JOIN movie m ON h.content_id = m.content_id
            JOIN content c ON m.content_id = c.content_id
            WHERE h.user_id = ?
        """;
        return jdbcTemplate.queryForList(sql, userId);
    }

    // ✅ Query 5: Streaming Trend in the Last 24 Hours
    public List<Map<String, Object>> getStreamingTrendLast24Hours() {
        String sql = """
            SELECT 
                c.title AS content_title,
                COUNT(h.stream_id) AS stream_count,
                MAX(h.watch_time) AS last_stream_time
            FROM (
                SELECT stream_id, content_id, movie_watch_time AS watch_time FROM movie_history
                UNION ALL
                SELECT stream_id, content_id, episode_watch_time AS watch_time FROM episode_history
            ) h
            JOIN content c ON h.content_id = c.content_id
            WHERE h.watch_time >= NOW() - INTERVAL 1 DAY
            GROUP BY c.title
            ORDER BY last_stream_time DESC;
        """;
        return jdbcTemplate.queryForList(sql);
    }

    // ✅ Query 6: Top 10 Movies/Series in the Last Month
    public List<Map<String, Object>> getTopTenLastMonth() {
        String sql = """
            SELECT 
                c.title AS content_title,
                COUNT(h.stream_id) AS total_streams
            FROM (
                SELECT stream_id, content_id, movie_watch_time AS watch_time FROM movie_history
                UNION ALL
                SELECT stream_id, content_id, episode_watch_time AS watch_time FROM episode_history
            ) h
            JOIN content c ON h.content_id = c.content_id
            WHERE h.watch_time >= NOW() - INTERVAL 1 MONTH
            GROUP BY c.title
            ORDER BY total_streams DESC
            LIMIT 10;
        """;
        return jdbcTemplate.queryForList(sql);
    }
        //User movie watch history
        public List<Map<String, Object>> getMovieHistory (String userId)
        {
            String sql = """
        SELECT content.title AS watched_movie, movie_history.movie_watch_time AS watch_time
        FROM movie_history
        JOIN movie ON movie_history.content_id = movie.content_id
        JOIN content ON movie.content_id = content.content_id
        WHERE movie_history.user_id = ?
        ORDER BY movie_history.movie_watch_time DESC
    """;

            return jdbcTemplate.queryForList(sql, userId);
        }

        //User series watch history
        public List<Map<String, Object>> getEpisodeHistory(String userId){
            String sql = """
                            SELECT content.title AS series_title,
                                   episode.title AS episode_title,
                                   episode_history.season_num,
                                   episode_history.episode_watch_time AS watch_time
                            FROM episode_history
                            JOIN series ON episode_history.content_id = series.content_id
                            JOIN content ON series.content_id = content.content_id
                            JOIN episode ON episode_history.episode_id = episode.episode_id
                            WHERE episode_history.user_id = ?
                            ORDER BY episode_history.episode_watch_time DESC
                        """;
            return jdbcTemplate.queryForList(sql, userId);
        }


        //User combined watch history
        public List<Map<String, Object>> getAllHistory(String userId)
        {
            String sql = """
        SELECT
            content.title AS title,
            NULL AS episode_title,
            movie_history.movie_watch_time AS watch_time,
            'Movie' AS type
        FROM movie_history
        JOIN movie ON movie_history.content_id = movie.content_id
        JOIN content ON movie.content_id = content.content_id
        WHERE movie_history.user_id = ?

        UNION ALL

        SELECT
            content.title AS title,
            episode.title AS episode_title,
            episode_history.episode_watch_time AS watch_time,
            'Episode' AS type
        FROM episode_history
        JOIN series ON episode_history.content_id = series.content_id
        JOIN content ON series.content_id = content.content_id
        JOIN episode ON episode_history.episode_id = episode.episode_id
        WHERE episode_history.user_id = ?

        ORDER BY watch_time DESC;
    """;
            return jdbcTemplate.queryForList(sql, userId, userId);
        }

    //Log a movie once it is streamed in the movie watch history
    public void logMovieStream(String contentId, String userId)
    {
        String streamId = "MH-" + UUID.randomUUID();

        String sql = """
        INSERT INTO movie_history (stream_id, movie_watch_time, user_id, content_id)
        VALUES (?, NOW(), ?, ?)
        """;

        jdbcTemplate.update(sql, streamId, userId, contentId);
    }

    //Log an episode once it is streamed in an episode history
    public void logEpisodeStream(String userId, String contentId, String episodeId, int seasonNum) {
        String streamId = "EH-" + UUID.randomUUID();

        String sql = """
        INSERT INTO episode_history (stream_id, episode_watch_time, user_id, content_id, episode_id, season_num)
        VALUES (?, NOW(), ?, ?, ?, ?)
    """;

        jdbcTemplate.update(sql, streamId, userId, contentId, episodeId, seasonNum);
    }
}

