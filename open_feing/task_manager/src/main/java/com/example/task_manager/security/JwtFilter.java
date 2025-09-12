package com.example.task_manager.security;

import com.example.task_manager.fiengclient.JwtServiceClient;
import com.example.task_manager.service.UserService;
import com.example.task_manager.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtServiceClient jwtServiceClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {



        System.out.println("In JWT filter");
        String authentication = request.getHeader("Authorization");
        System.out.println(authentication);
        String jwtToken = "";

        if (authentication != null && authentication.startsWith("Bearer ")) {
            jwtToken = authentication.substring(7);
            System.out.println("JWT token : " + jwtToken);
        } else {
            System.out.println("In jwt filter 1 else");
            filterChain.doFilter(request, response);
           return;
        }
        jwtUtil.setType("access");

        String userId = jwtUtil.extractUsername(jwtToken);

        System.out.println("User id : " + userId);
        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
            boolean isTokenValid = jwtUtil.validateToken(jwtToken);

            if (isTokenValid) {
                Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("JWT token is valid");
            }
        }
        filterChain.doFilter(request, response);
    }
}
