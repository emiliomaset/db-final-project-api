package com.example.streaming_service.controller;

import com.example.streaming_service.model.Content;
import com.example.streaming_service.model.Episode;
import com.example.streaming_service.repository.ContentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Service
@RestController
public class SearchController
{

//    @GetMapping("/search")
//    public String search(@RequestParam(name = "q", required = false) String query)
//    {
//        if (query == null || query.isBlank())
//        {
//            return "Please type SOMETHING in...";
//            //return new RedirectView("https://www.google.com/");
//        }
//        return ContentRepository.findByTitleContaining(query.trim());          // Echo back exactly what was asked
//        //return new RedirectView(target, true);
//    }
}