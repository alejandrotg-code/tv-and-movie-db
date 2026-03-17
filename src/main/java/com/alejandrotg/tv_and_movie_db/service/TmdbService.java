package com.alejandrotg.tv_and_movie_db.service;

import com.alejandrotg.tv_and_movie_db.model.Content;
import com.alejandrotg.tv_and_movie_db.model.ContentType;
import com.alejandrotg.tv_and_movie_db.model.Genre;
import com.alejandrotg.tv_and_movie_db.repository.GenreRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class TmdbService {


    @Value("${tmdb.api.key}")
    private String apiKey;

    @Autowired
    GenreRepository genreRepository;

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

    public String getByGenre(int genreId) {
        return webClient.get()
                .uri("/discover/movie?with_genres={genreId}&api_key={key}", genreId, apiKey)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    // Mapear json
    public Content fetchAndMapContent(int tmdbId, String mediaType, ObjectMapper objectMapper) {
        String json = getDetails(tmdbId, mediaType);
        try {
            JsonNode node = objectMapper.readTree(json);
            Content content = new Content();
            content.setTmdbId(tmdbId);

            // Si es 'movie' se asigna MOVIE, si no TV
            content.setMediaType(mediaType.equals("movie") ? ContentType.MOVIE : ContentType.TV);

            content.setTitle(node.has("title") ? node.get("title").asText() : node.get("name").asText());
            content.setOverview(node.get("overview").asText());

            // poster_path y backdrop_path pueden ser null en la respuesta de TMDB
            content.setPosterPath(node.get("poster_path").asText(null));
            content.setBackdropPath(node.get("backdrop_path").asText(null));

            content.setOriginalLanguage(node.get("original_language").asText());
            content.setVoteAverage(node.get("vote_average").asDouble());
            content.setPopularity(node.get("popularity").asDouble());
            // Genres
            List<Genre> genres = new ArrayList<>();
            JsonNode genreArray = node.get("genres");
            if (genreArray != null && genreArray.isArray()){
                for (JsonNode g: genreArray){
                    int gTmdbId = g.get("id").asInt();
                    String gName = g.get("name").asText();

                    // Busca el género en DB; si no existe, lo crea y persiste
                    Genre genre = genreRepository.findByTmdbId(gTmdbId)
                            .orElseGet(() -> {
                                Genre newGenre = new Genre();
                                newGenre.setTmdbId(gTmdbId);
                                newGenre.setName(gName);
                                return genreRepository.save(newGenre);
                            });
                    genres.add(genre);
                }
            }
            content.setGenres(genres);
            // Las películas usan 'release_date', las series 'first_air_date'
            String dateField = mediaType.equals("movie") ? "release_date" : "first_air_date";
            JsonNode dateNode = node.get(dateField);
            if(dateNode != null && !dateNode.asText().isEmpty()) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    content.setReleaseDate(sdf.parse(dateNode.asText()));
                } catch (Exception ignored) {
                }
            }
            return content;
        } catch (Exception e){
            throw new RuntimeException("Error parseando la respuesta de TMDB: ", e);
        }
    }
}
