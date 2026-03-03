package com.alejandrotg.tv_and_movie_db.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "content")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int tmdbId;
    private String title;
    private String overview;
    private String posterPath;
    private String backdropPath;
    @Enumerated(EnumType.STRING)
    private ContentType mediaType;
    private String originalLanguage;
    private Date releaseDate;
    private double voteAverage;
    private double popularity;
}
