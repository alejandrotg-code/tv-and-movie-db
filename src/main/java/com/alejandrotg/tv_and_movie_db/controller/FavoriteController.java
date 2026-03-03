package com.alejandrotg.tv_and_movie_db.controller;

import com.alejandrotg.tv_and_movie_db.model.Favorite;
import com.alejandrotg.tv_and_movie_db.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @PostMapping
    public Favorite addFavorite(@RequestBody Favorite favorite){
        return favoriteService.addFavorite(favorite);
    }

    @DeleteMapping("/{id}")
    public void deleteFavorite(@PathVariable Long id){
        favoriteService.removeFavorite(id);
    }

    @GetMapping("/user/{userId}")
    public List<Favorite> getFavoritesByUser(@PathVariable Long userId) {
        return favoriteService.getFavoritesByUser(userId);
    }
}
