package com.alejandrotg.tv_and_movie_db.service;

import com.alejandrotg.tv_and_movie_db.model.Rating;
import com.alejandrotg.tv_and_movie_db.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private TmdbService tmdbService;

    public String getRecommendations(Long userId) {

        List<Rating> highRatings = ratingRepository.findByUserIdAndScoreGreaterThanEqual(userId, 7);

        if (highRatings.isEmpty()) {
            return tmdbService.getTrending();
        }


        List<Integer> genreIds = highRatings.stream()
                .flatMap(r -> r.getContent().getGenres().stream())
                .map(g -> g.getTmdbId())
                .distinct()
                .toList();


        int topGenre = genreIds.get(0);
        return tmdbService.getByGenre(topGenre);
    }
}
