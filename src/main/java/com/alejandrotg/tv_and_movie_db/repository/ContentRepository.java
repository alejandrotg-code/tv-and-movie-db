package com.alejandrotg.tv_and_movie_db.repository;

import com.alejandrotg.tv_and_movie_db.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    Optional<Content> findByTmdbId(int tmdbId);

}
