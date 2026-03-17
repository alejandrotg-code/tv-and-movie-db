package com.alejandrotg.tv_and_movie_db.controller;

import com.alejandrotg.tv_and_movie_db.dto.RatingRequestDTO;
import com.alejandrotg.tv_and_movie_db.dto.RatingResponseDTO;
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
    public RatingResponseDTO addRating(@RequestBody RatingRequestDTO request){
        return ratingService.addRating(request);
    }

    @GetMapping("/user/{userId}")
    public List<RatingResponseDTO> getRatingsByUser(@PathVariable Long userId) {
        return ratingService.getRatingsByUser(userId);
    }

    @DeleteMapping("/{id}")
    public void removeRating(@PathVariable Long id){
        ratingService.removeRating(id);
    }

    @GetMapping("/content/{contentId}")
    public List<RatingResponseDTO> getRatingsByContent(@PathVariable Long contentId) {
        return ratingService.getRatingsByContent(contentId);
    }

}
