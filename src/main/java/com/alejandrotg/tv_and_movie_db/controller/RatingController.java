package com.alejandrotg.tv_and_movie_db.controller;

import com.alejandrotg.tv_and_movie_db.model.Rating;
import com.alejandrotg.tv_and_movie_db.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping
    public Rating addRating(@RequestBody Rating rating){
        return ratingService.addRating(rating);
    }

    @GetMapping("/user/{userId}")
    public List<Rating> getRatingsByUser(@PathVariable Long userId) {
        return ratingService.getRatingsByUser(userId);
    }

    @DeleteMapping("/{id}")
    public void removeRating(@PathVariable Long id){
        ratingService.removeRating(id);
    }

    @GetMapping("/content/{contentId}")
    public List<Rating> getRatingsByContent(@PathVariable Long contentId) {
        return ratingService.getRatingsByContent(contentId);
    }

}
