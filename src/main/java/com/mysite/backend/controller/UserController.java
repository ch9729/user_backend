package com.mysite.backend.controller;

import com.mysite.backend.exception.UserNotFoundException;
import com.mysite.backend.model.User;
import com.mysite.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/users")
    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/users/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        // return userRepository.findById(id).map(user -> {
        //     user.setUsername(newUser.getUsername());
        //     user.setName(newUser.getName());
        //     user.setEmail(newUser.getEmail());
        //     return userRepository.save(user);
        // }).orElseThrow(() -> new UserNotFoundException(id));
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        // 찾은 유저에 새유저 내용으로 변경
        user.setUsername(newUser.getUsername());
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        return userRepository.save(user);
    }
}
