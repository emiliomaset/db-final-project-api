package com.example.streaming_service.controller;

import com.example.streaming_service.model.Content;
import com.example.streaming_service.model.Episode;
import com.example.streaming_service.repository.ContentRepository;
import com.example.streaming_service.service.LoginService;
import com.example.streaming_service.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchController
{
    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public List search(@RequestParam(name = "query", required = false) String query,
                       @RequestParam(name = "award", required = false) boolean awardCheck,
                       @RequestParam(name = "watched", required = false) boolean watchedCheck)
    {
        if (query == null || query.isBlank())
        {
            return new ArrayList<String>();
        }

        return searchService.search(query.trim(), awardCheck, watchedCheck);          // Echo back exactly what was asked
    }

}