package com.alejandrotg.tv_and_movie_db.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "ratings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_content")
    private Content content;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    private String comment;
    private int score;

    private LocalDate addedAt;
}
