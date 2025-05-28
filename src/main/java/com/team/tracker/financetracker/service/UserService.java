package com.team.tracker.financetracker.service;


import com.team.tracker.financetracker.exceptions.UserNotFoundException;
import com.team.tracker.financetracker.model.User;
import com.team.tracker.financetracker.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public User save(User user) throws BadRequestException {

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new BadRequestException("Имя пользователя уже занято");
        }

        user.setCreationDate(LocalDate.now());

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    //Нахождения пользователя по имени
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username)); // получаем пользователя
    }

    //Проверка введенного пароля с паролем пользователя
    public boolean checkPassword(User user, String inputPassword){
        return bCryptPasswordEncoder.matches(inputPassword, user.getPassword());
    }

    public User findById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
