package com.team.tracker.financetracker.controller;

import com.team.tracker.financetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.team.tracker.financetracker.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> registerUser(@RequestBody User user) {

//        if (userRepository.existsByUsername(username)){
//            model.addAttribute("error", "Username already exists");
//        }
//
//        if (username == null || username.trim().isEmpty()) {
//            model.addAttribute("error", "User name cannot be empty.");
//            return "/register";
//        }
//
//        if (password == null || password.trim().isEmpty()) {
//            model.addAttribute("error", "Password cannot be empty.");
//            return "/register";
//        }
//
//        User user = new User(username, bCryptPasswordEncoder.encode(password));
//        userRepository.save(user);

        userService.save(user);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}

//rest controller
//todo requestBody