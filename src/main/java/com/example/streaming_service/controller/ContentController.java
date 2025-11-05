package com.example.streaming_service.controller;

import com.example.streaming_service.model.Content;
import com.example.streaming_service.model.Movie;
import com.example.streaming_service.repository.MovieHistoryRepository;
import com.example.streaming_service.repository.MovieRepository;
import com.example.streaming_service.repository.SeriesRepository;
import com.example.streaming_service.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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




}
