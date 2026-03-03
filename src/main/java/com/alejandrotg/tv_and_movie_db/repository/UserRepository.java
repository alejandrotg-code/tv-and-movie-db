package com.alejandrotg.tv_and_movie_db.repository;

import com.alejandrotg.tv_and_movie_db.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}