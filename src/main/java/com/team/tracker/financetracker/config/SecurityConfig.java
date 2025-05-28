package com.team.tracker.financetracker.config;

import com.team.tracker.financetracker.security.JwtFIlter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JwtFIlter jwtFIlter;

    public SecurityConfig(JwtFIlter jwtFIlter){
        this.jwtFIlter = jwtFIlter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(corsConfigurationSource()).and()
                .csrf()
                .disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/register", "/api/v1/auth/login", "/api/v1/categories", "/api/v1/transaction").permitAll() //ДЛЯ ТЕСТА "/api/v1/categories", "/api/v1/transaction"
                        .anyRequest().authenticated()
                )
                //.anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFIlter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {

        return config.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:63342")); // Разрешенные origin
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Разрешенные методы
        configuration.setAllowedHeaders(Arrays.asList("*")); // Разрешенные заголовки
        configuration.setAllowCredentials(true); // Разрешаем передачу куки и авторизационных заголовков

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Применяем ко всем путям
        return source;
    }
}