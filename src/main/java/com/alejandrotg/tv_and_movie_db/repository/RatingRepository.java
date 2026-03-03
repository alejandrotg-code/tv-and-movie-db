package com.alejandrotg.tv_and_movie_db.repository;

import com.alejandrotg.tv_and_movie_db.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

}
