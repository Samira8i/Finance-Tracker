package com.team.tracker.financetracker.service;


import com.team.tracker.financetracker.model.User;
import com.team.tracker.financetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
    public void save(User user) {

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
    }
    //Нахождения пользователя по имени
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username); // получаем пользователя

        if (user == null) { // если пользователь не найден
            throw new UsernameNotFoundException("User not found"); // выбрасываем исключение
        }

        return user; // если найден — возвращаем
    }
    //Проверка введенного пароля с паролем пользователя
    public boolean checkPassword(User user, String inputPassword){
        return bCryptPasswordEncoder.matches(inputPassword, user.getPassword());
    }
}
