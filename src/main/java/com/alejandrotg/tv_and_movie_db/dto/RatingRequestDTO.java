package com.alejandrotg.tv_and_movie_db.dto;

/**
 * DTO para crear un nuevo rating.
 * mediaType debe ser "movie" o "tv".
 * score debe estar entre 1 y 10.
 */
public record RatingRequestDTO(
        int tmdbId,
        String mediaType,
        String comment,
        int score
) {
}
