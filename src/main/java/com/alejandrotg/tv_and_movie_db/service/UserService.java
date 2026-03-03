package com.alejandrotg.tv_and_movie_db.service;

import com.alejandrotg.tv_and_movie_db.model.AccountStatus;
import com.alejandrotg.tv_and_movie_db.model.User;
import com.alejandrotg.tv_and_movie_db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getById(Long id){
        return userRepository.findById(id).orElse(null);
    }
    public User addAccount(User user) {
        return userRepository.save(user);
    }

    public User updateAccount(Long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    public void desactivateAccount(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setStatus(AccountStatus.INACTIVE);
            userRepository.save(user);
        }
    }
    public void deleteAccount(Long id) {
        userRepository.deleteById(id);
    }
}
