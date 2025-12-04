package com.example.streaming_service.service;

import com.example.streaming_service.dto.ContentDTO;
import com.example.streaming_service.dto.EpisodeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContentManagementService {

    private final JdbcTemplate jdbcTemplate;

    public ContentManagementService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Gets all content
    public List<HashMap<String, Object>> getAllContent() {

        String sql = """
                SELECT c.content_id, c.title, c.release_date, c.genre, c.imdb_link,
                       IF(m.content_id IS NOT NULL, 'movie', 'series') AS type,
                       m.duration,
                       s.num_seasons
                FROM content c
                LEFT JOIN movie m ON c.content_id = m.content_id
                LEFT JOIN series s ON c.content_id = s.content_id
                ORDER BY c.title ASC
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            HashMap<String, Object> map = new HashMap<>();

            map.put("contentId", rs.getString("content_id"));
            map.put("title", rs.getString("title"));
            map.put("releaseDate", rs.getString("release_date"));
            map.put("genre", rs.getString("genre"));
            map.put("imdbLink", rs.getString("imdb_link"));
            map.put("type", rs.getString("type"));
            map.put("duration", rs.getString("duration"));
            map.put("numSeasons", rs.getObject("num_seasons"));

            return map;
        });
    }

    //Remove content
    public ResponseEntity<HttpStatus> removeContent(String contentId) {

        jdbcTemplate.update("DELETE FROM movie WHERE content_id = ?", contentId);
        jdbcTemplate.update("DELETE FROM series WHERE content_id = ?", contentId);
        jdbcTemplate.update("DELETE FROM episode WHERE content_id = ?", contentId);

        String sql = "DELETE FROM content WHERE content_id = ?";
        int rows = jdbcTemplate.update(sql, contentId);

        if (rows == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Add new content
    public ResponseEntity<String> addContent(ContentDTO dto) {

        Random random = new Random();
        String generatedId = "C" + random.nextInt(10000);

        // Insert into CONTENT table
        String sql1 = """
                INSERT INTO content (content_id, title, release_date, genre, imdb_link)
                VALUES (?, ?, ?, ?, ?)
                """;

        int result1 = jdbcTemplate.update(sql1,
                generatedId,
                dto.getTitle(),
                dto.getReleaseDate(),
                dto.getGenre(),
                dto.getImdbLink()
        );

        if (result1 == 0) {
            return new ResponseEntity<>("", HttpStatus.NOT_IMPLEMENTED);
        }

        // Insert into child table based on type
        if (dto.getType().equalsIgnoreCase("movie")) {

            String sqlMovie = """
                    INSERT INTO movie (content_id, duration)
                    VALUES (?, ?)
                    """;

            int result2 = jdbcTemplate.update(sqlMovie,
                    generatedId,
                    dto.getDuration()
            );

            if (result2 == 0)
                return new ResponseEntity<>("", HttpStatus.NOT_IMPLEMENTED);

        } else if (dto.getType().equalsIgnoreCase("series")) {

            String sqlSeries = """
                    INSERT INTO series (content_id, num_seasons)
                    VALUES (?, ?)
                    """;

            int result2 = jdbcTemplate.update(sqlSeries,
                    generatedId,
                    dto.getNumSeasons()
            );

            if (result2 == 0)
                return new ResponseEntity<>("", HttpStatus.NOT_IMPLEMENTED);
        }

        return new ResponseEntity<>(generatedId, HttpStatus.OK);
    }

    //Adds new episodes after series creation
    public ResponseEntity<HttpStatus> addEpisodes(List<EpisodeDTO> episodes) {
        Random random = new Random();

        String sql = """
            INSERT INTO episode (episode_id, title, duration, season_num, content_id)
            VALUES (?, ?, ?, ?, ?)
            """;

        for (EpisodeDTO ep : episodes) {
            String episodeId = "E" + random.nextInt(10000); //Upper bound chosen by Emilio, just keepin' 'er consistent :)

            int result = jdbcTemplate.update(sql,
                    episodeId,
                    ep.getTitle(),
                    ep.getDuration(),
                    ep.getSeasonNum(),
                    ep.getContentId()
            );

            if (result == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
