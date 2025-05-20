package com.team.tracker.financetracker.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.security.Key;
import java.io.IOException;


@Component
public class JwtFIlter extends OncePerRequestFilter {

    private final CustomUserDetailsService customUserDetailsService;
    private final  JwtTokenUtil jwtTokenUtil;

    public JwtFIlter(CustomUserDetailsService customUserDetailsService, JwtTokenUtil jwtTokenUtil){
        this.customUserDetailsService = customUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String autorizationHeader = request.getHeader("Autorization");

        if (autorizationHeader == null || !autorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        String token = autorizationHeader.substring(7);
        Key key = jwtTokenUtil.getSigningKey();

        if (!jwtTokenUtil.validateToken(token,key)){
            response.sendError(401, "Неправильный токен");
            return;
        }

        String username = jwtTokenUtil.extractUsername(token);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );


        authToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request,response);
    }

}
