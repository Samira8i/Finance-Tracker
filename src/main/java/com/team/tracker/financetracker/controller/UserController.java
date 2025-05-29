package com.team.tracker.financetracker.controller;

import com.team.tracker.financetracker.repository.UserRepository;
import com.team.tracker.financetracker.security.CustomUserDetailsService;
import com.team.tracker.financetracker.security.JwtTokenUtil;
import com.team.tracker.financetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.team.tracker.financetracker.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:63342")
public class    UserController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService customUserDetailsService;



    @Autowired
    public UserController(AuthenticationManager authenticationManager, UserService userService, JwtTokenUtil jwtTokenUtil, CustomUserDetailsService customUserDetailsService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.customUserDetailsService = customUserDetailsService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        if (user.getUsername().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Имя пользователя не может быть пустым");
        }

        if (user.getPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Пароль не может быть пустым");
        }

        try {
            return ResponseEntity.ok(userService.save(user));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Ошибка при регистрации");
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User inputUser){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(inputUser.getUsername(), inputUser.getPassword()));

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(inputUser.getUsername());
            String accesToken = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(accesToken);
        }
        catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Неверный логин или пароль");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body("Пользователь не найден");
        }

    }
}

