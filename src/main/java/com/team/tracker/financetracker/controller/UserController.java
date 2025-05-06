package com.team.tracker.financetracker.controller;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import com.team.tracker.financetracker.model.User;
import com.team.tracker.financetracker.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Date;


@Controller
public class UserController {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public void registerUser(@RequestParam String username, @RequestParam String password, Model model) {

        if (userRepository.existsByUsername(username)){
            model.addAttribute("error", "Username already exists"); //для вывода ошибки
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));//Bcrypt
        user.setDate(new Date());
        userRepository.save(user);


    }
}
//todo авторизщация регистрация / навигация
//todo перенести логику в
//бэк только на апи без навигации
//rest controller
//todo requestBody