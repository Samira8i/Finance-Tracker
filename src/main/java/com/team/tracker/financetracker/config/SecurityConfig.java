package com.team.tracker.financetracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/home", "/login", "/register").permitAll() //"/css/**", "/js/**, "/images/**"
                        .anyRequest().authenticated()
                );
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/mainPage")
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/home")
//                        .permitAll()
//                );

        return http.build();
    }
}