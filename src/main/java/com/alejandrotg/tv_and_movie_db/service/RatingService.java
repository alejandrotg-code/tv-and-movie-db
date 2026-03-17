package com.alejandrotg.tv_and_movie_db.service;

import com.alejandrotg.tv_and_movie_db.dto.RatingRequestDTO;
import com.alejandrotg.tv_and_movie_db.dto.RatingResponseDTO;
import com.alejandrotg.tv_and_movie_db.model.Content;
import com.alejandrotg.tv_and_movie_db.model.Rating;
import com.alejandrotg.tv_and_movie_db.model.User;
import com.alejandrotg.tv_and_movie_db.repository.RatingRepository;
import com.alejandrotg.tv_and_movie_db.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    ContentService contentService;

    @Autowired
    TmdbService tmdbService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;



    public RatingResponseDTO addRating(RatingRequestDTO request) {
        // Busca el content en tu DB
        Content content = contentService.getByTmdbId(request.tmdbId());

        // Si no existe, lo fetcha de TMDB y lo persiste
        if (content == null) {
            content = tmdbService.fetchAndMapContent(request.tmdbId(), request.mediaType(), objectMapper);
            content = contentService.saveContent(content);
        }

        // Construye y guarda el rating
        Rating rating = new Rating();
        rating.setContent(content);
        rating.setComment(request.comment());
        rating.setScore(request.score());
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        rating.setUser(user);
        rating.setAddedAt(LocalDate.now());

        Rating saved = ratingRepository.save(rating);
        return toDTO(saved);
    }
    private RatingResponseDTO toDTO(Rating r) {
        return new RatingResponseDTO(
                r.getId(),
                r.getContent() != null ? r.getContent().getId() : null,
                r.getContent() != null ? r.getContent().getMediaType().name() : null,
                r.getContent() != null ? r.getContent().getTitle() : null,
                r.getUser() != null ? r.getUser().getId() : null,
                r.getUser() != null ? r.getUser().getUsername() : null,
                r.getComment(),
                r.getScore(),
                r.getAddedAt() != null ? r.getAddedAt().atStartOfDay() : null
        );
    }

    public List<RatingResponseDTO> getRatingsByUser(Long userId) {
        return ratingRepository.findByUserId(userId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public RatingResponseDTO editRating(Long id, Rating rating) {
        rating.setId(id);
        Rating saved = ratingRepository.save(rating);
        return toDTO(saved);
    }

    public List<RatingResponseDTO> getRatingsByContent(Long contentId) {
        return ratingRepository.findByContentId(contentId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public void removeRating(Long id){
        ratingRepository.deleteById(id);
    }


}
