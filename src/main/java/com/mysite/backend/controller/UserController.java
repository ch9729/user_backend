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

    // 유저 추가
    @PostMapping("/users")
    User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    //유저의 목록 확인
    @GetMapping("/users")
    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 없는 유저의id값을 검색했을시 오류 출력
    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    // 유저id값을 들고와 업데이트(수정을 할시 putMapping를 사용한다(업데이트))
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

    // 유저 id값을 받아와 삭제
    @DeleteMapping("/users/{id}")
    String deleteUser(@PathVariable Long id) {
        // existsById는 boolean타입으로 존재하는지 존재하지 않는지만을 확인할때 사용
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "유저아이디: " + id + "는 삭제 되었습니다.";
    }
}
