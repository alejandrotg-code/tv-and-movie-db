package com.alejandrotg.tv_and_movie_db.dto;

//record response
public record RatingResponseDTO(
        Long id,
        Long contentId,
        String mediaType, // MOVIE or TV
        String contentTitle,
        Long userId,
        String username,
        String comment,
        int score,
        java.time.LocalDateTime addedAt
) {}
