package com.alejandrotg.tv_and_movie_db.controller;

import com.alejandrotg.tv_and_movie_db.model.User;
import com.alejandrotg.tv_and_movie_db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id){
        return userService.getById(id);
    }
    @PostMapping
    public User addAcount(@RequestBody User user){
        return userService.addAccount(user);
    }
    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id){
        userService.deleteAccount(id);
    }
    @PutMapping("/{id}")
    public User updateAccount(@PathVariable Long id, @RequestBody User user){
        return userService.updateAccount(id, user);
    }
    @PutMapping("/{id}/desactivate")
    public void desactivateAccount(@PathVariable Long id){
        userService.desactivateAccount(id);
    }

}
