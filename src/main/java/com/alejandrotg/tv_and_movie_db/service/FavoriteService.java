package com.alejandrotg.tv_and_movie_db.service;


import com.alejandrotg.tv_and_movie_db.model.Favorite;
import com.alejandrotg.tv_and_movie_db.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    public Favorite addFavorite(Favorite favorite){
        return favoriteRepository.save(favorite);
    }

    public void removeFavorite(Long id) {
        favoriteRepository.deleteById(id);
    }

    public List<Favorite> getFavoritesByUser(Long userId) {
        return favoriteRepository.findByUserId(userId);
    }
}
