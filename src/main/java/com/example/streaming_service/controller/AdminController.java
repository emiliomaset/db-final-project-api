package com.example.streaming_service.controller;

import com.example.streaming_service.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private MovieService movieService;

    // Admin: Streaming trend in the last 24 hours
    @GetMapping("/trends/last24hours")
    public List<Map<String, Object>> getStreamingTrendLast24HoursForAdmin() {
        return movieService.getStreamingTrendLast24Hours();
    }
}
