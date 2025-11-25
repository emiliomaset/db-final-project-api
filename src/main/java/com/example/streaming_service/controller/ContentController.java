package com.example.streaming_service.controller;

import com.example.streaming_service.model.Content;
import com.example.streaming_service.model.Episode;
import com.example.streaming_service.model.User;
import com.example.streaming_service.repository.*;
import com.example.streaming_service.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@Service
@RestController
public class ContentController {

    @Autowired
    private ContentService contentService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieHistoryRepository movieHistoryRepository;

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private EpisodeHistoryRepository episodeHistoryRepository;

    @GetMapping("/getallcontent")
    public ResponseEntity<List<Content>> getAllContent() {
        List<Content> content = contentService.getAllContent();
        return new ResponseEntity<>(content, HttpStatus.OK);
    }

    @PostMapping("/getmovieorseries")
    public String getMovieOrSeries(@RequestBody String contentId) {
        if (movieRepository.existsById(contentId))
            return "movie";
        else
            return "series";
    }

    @PostMapping("/getmovieviewcount")
    public String getMovieViewCount(@RequestBody String contentId) {
        return Integer.toString(movieHistoryRepository.countByContentId(contentId));
    }

    @PostMapping("/getnumseasons")
    public String getNumSeasonsByShow(@RequestBody String contentId) {
        return Integer.toString(seriesRepository.findByContentId(contentId).getNumSeasons());
    }

    @PostMapping("/getepisodes")
    public List<Episode> getEpisodesOfSeries(@RequestBody Episode episode) {
        return episodeRepository.findByContentIdAndSeasonNum(episode.getContentId(), episode.getSeasonNum());
    }

    @PostMapping("/getepisodeviewcount")
    public String getEpisodeViewCount(@RequestBody String episodeId) {
        return Integer.toString(episodeHistoryRepository.countByEpisodeId(episodeId));
    }

    @GetMapping("/getviewers/{contentId}/{contentType}")
    public List<?> getMembersWhoViewedAVideo(@PathVariable("contentId") String contentId, @PathVariable("contentType") String contentType) {
        return contentService.getViewers(contentId, contentType);

        // return orderService.createOrder(orderRequest.getUserId(), orderRequest.getProductId(), orderRequest.getQuantity());
    }

}
