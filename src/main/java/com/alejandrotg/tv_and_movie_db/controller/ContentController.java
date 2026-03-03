package com.alejandrotg.tv_and_movie_db.controller;

import com.alejandrotg.tv_and_movie_db.model.Content;
import com.alejandrotg.tv_and_movie_db.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping("/{id}")
    public Content getById(@PathVariable Long id) {
        return contentService.getById(id);
    }

    @PostMapping
    public Content saveContent(@RequestBody Content content) {
        return contentService.saveContent(content);
    }

    @GetMapping("/tmdb/{tmdbId}")
    public Content getByTmdbId(@PathVariable int tmdbId) {
        return contentService.getByTmdbId(tmdbId);
    }
}
