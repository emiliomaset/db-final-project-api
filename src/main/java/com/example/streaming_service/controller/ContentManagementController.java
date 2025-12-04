package com.example.streaming_service.controller;

import com.example.streaming_service.dto.ContentDTO;
import com.example.streaming_service.dto.EpisodeDTO;
import com.example.streaming_service.service.ContentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ContentManagementController {

    @Autowired
    private ContentManagementService contentManagementService;

    /*Get all content (this mapping already created elsewhere, commenting out)
    @GetMapping("/getallcontent")
    public ResponseEntity<?> getAllContent() {
        var content = contentManagementService.getAllContent();

        if (content.size() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(content, HttpStatus.OK);
    }
    */

    //Remove content
    @PostMapping("/removecontent/{contentId}")
    public ResponseEntity<HttpStatus> removeContent(@PathVariable String contentId) {
        return contentManagementService.removeContent(contentId);
    }

    @PostMapping("/addcontent")
    public ResponseEntity<String> addContent(@RequestBody ContentDTO dto) {
        return contentManagementService.addContent(dto);
    }

    @PostMapping("/addepisodes")
    public ResponseEntity<HttpStatus> addEpisodes(@RequestBody List<EpisodeDTO> episodes) {
        return contentManagementService.addEpisodes(episodes);
    }

}
