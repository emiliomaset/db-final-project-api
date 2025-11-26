package com.example.streaming_service.controller;

import com.example.streaming_service.service.MovieService;
import com.example.streaming_service.dto.MovieStreamRequest;
import com.example.streaming_service.dto.EpisodeStreamRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/movies")
public class MovieController
{

    @Autowired
    private MovieService movieService;

    //User information controller
    @GetMapping("/member-info/{email}")
    public Map<String, Object> getMemberInfo(@PathVariable String email)
    {
        return movieService.getMemberInfo(email);
    }

    //Movie information controller
    @GetMapping("/all")
    public List<Map<String, Object>> getAllMovies()
    {
        return movieService.getAllMovies();
    }

    //All sequel connections controller
    @GetMapping("/sequels")
    public List<Map<String, Object>> getSequels()
    {
        return movieService.getSequels();
    }

    //Movie sequel connection controller
    @GetMapping("/sequels/{contentId}")
    public ResponseEntity<List<Map<String, Object>>> getMovieSequel(@PathVariable String contentId)
    {
        List<Map<String, Object>> sequels = movieService.getMovieSequel(contentId);
        return ResponseEntity.ok(sequels);
    }

    //Movie history controller
    @GetMapping("/movie-history/{userId}")
    public List<Map<String, Object>> getMovieHistory(@PathVariable String userId)
    {
        return movieService.getMovieHistory(userId);
    }

    //Episode history controller
    @GetMapping("/episode-history/{userId}")
    public List<Map<String, Object>> getEpisodeHistory(@PathVariable String userId)
    {
        return movieService.getEpisodeHistory(userId);
    }

    //All history controller
    @GetMapping("/all-history/{userId}")
    public List<Map<String, Object>> getCombinedHistory(@PathVariable String userId)
    {
        return movieService.getAllHistory(userId);
    }
    // Streaming trend in the last 24 hours
    @GetMapping("/trends/last24hours")
    public List<Map<String, Object>> getStreamingTrendLast24Hours() {
        return movieService.getStreamingTrendLast24Hours();
    }

    // Top 10 movies/series in the last month
    @GetMapping("/top10/lastmonth")
    public List<Map<String, Object>> getTopTenLastMonth() {
        return movieService.getTopTenLastMonth();
    }

    //Movie stream controller
    @PostMapping("/stream/movie")
    public ResponseEntity<Void> streamMovie(@RequestBody MovieStreamRequest request)
    {
        if (request.getUserId() == null || request.getContentId() == null)
        {
            return ResponseEntity.badRequest().build();
        }

        movieService.logMovieStream(request.getContentId(), request.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //Episode stream controller
    @PostMapping("/stream/episode")
    public ResponseEntity<Void> streamEpisode(@RequestBody EpisodeStreamRequest request) {

        if (request.getUserId() == null || request.getContentId() == null || request.getEpisodeId() == null)
        {
            return ResponseEntity.badRequest().build();
        }

        movieService.logEpisodeStream(
                request.getUserId(),
                request.getContentId(),
                request.getEpisodeId(),
                request.getSeasonNum()
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
