package com.alejandrotg.tv_and_movie_db.service;

import com.alejandrotg.tv_and_movie_db.model.Content;
import com.alejandrotg.tv_and_movie_db.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentService {

    @Autowired
    ContentRepository contentRepository;

    public Content saveContent(Content content) {
        return contentRepository.save(content);
    }

    public Content getById(Long id){
        return contentRepository.findById(id).orElse(null);
    }

    public Content getByTmdbId(int tmdbId) {
        return contentRepository.findByTmdbId(tmdbId).orElse(null);
    }

}
