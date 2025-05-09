package com.team.tracker.financetracker.controller;

import com.team.tracker.financetracker.repository.UserRepository;
import com.team.tracker.financetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.team.tracker.financetracker.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        if (user.getUsername().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Имя пользователя не может быть пустым");
        }

        if (user.getPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Пароль не может быть пустым");
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Имя пользователя уже занято");
        }

        try {
            userService.save(user);
            return ResponseEntity.ok("Пользователь успешно зарегистрирован");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Ошибка при регистрации");
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User inputUser){
        try {

            User user = userService.findByUsername(inputUser.getUsername());

            if (userService.checkPassword(user, inputUser.getPassword())) {
                return ResponseEntity.ok("Успешный вход");
            } else {
                return ResponseEntity.status(401).body("Неверный пароль");
            }
        }
        catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body("Пользователь не найден");
        }
    }
}

