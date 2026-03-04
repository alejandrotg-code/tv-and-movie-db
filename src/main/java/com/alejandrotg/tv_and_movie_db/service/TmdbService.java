package com.alejandrotg.tv_and_movie_db.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TmdbService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    private final String BASE_URL = "https://api.themoviedb.org/3";

    private final WebClient webClient = WebClient.create(BASE_URL);

    public String searchContent(String query) {
        return webClient.get()
                .uri("/search/multi?query={query}&api_key={key}", query, apiKey)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String getTrending() {
        return webClient.get()
                .uri("/trending/all/week?api_key={key}", apiKey)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String getDetails(int tmdbId, String mediaType) {
        return webClient.get()
                .uri("/{mediaType}/{id}?api_key={key}", mediaType, tmdbId, apiKey)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
