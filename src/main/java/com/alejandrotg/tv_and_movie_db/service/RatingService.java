package com.alejandrotg.tv_and_movie_db.service;

import com.alejandrotg.tv_and_movie_db.model.Rating;
import com.alejandrotg.tv_and_movie_db.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    public Rating addRating(Rating rating){
        return ratingRepository.save(rating);
    }

    public List<Rating> getRatingsByUser(Long userId) {
        return ratingRepository.findByUserId(userId);
    }

    public Rating editRating(Long id, Rating rating){
        rating.setId(id);
        return ratingRepository.save(rating);
    }

    public void removeRating(Long id){
        ratingRepository.deleteById(id);
    }

    public List<Rating> getRatingsByContent(Long contentId) {
        return ratingRepository.findByContentId(contentId);
    }
}
