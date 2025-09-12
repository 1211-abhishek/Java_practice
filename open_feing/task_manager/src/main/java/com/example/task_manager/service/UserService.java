package com.example.task_manager.service;

import com.example.task_manager.dto.AuthReqRes;
import com.example.task_manager.dto.UserDTO;
import com.example.task_manager.entity.Roles;
import com.example.task_manager.entity.Users;
import com.example.task_manager.repository.RoleRepository;
import com.example.task_manager.repository.UserRepo;
import com.example.task_manager.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Users> getAllUsers() {

        return userRepo.findAll();
    }

    public Users getUser(String userName) {

        return userRepo.findById(userName).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Users postUser(UserDTO userDTO) {

        Users user = new Users();
        Set<String> roles = userDTO.getRoles();

        Set<Roles> dbRoles = roles.stream().map(roleName -> roleRepository.findByRole(roleName).orElseThrow(() -> new RuntimeException("Invalid Role"))).collect(Collectors.toSet());

        user.setRoles(dbRoles);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setUserName(userDTO.getUserName());
        return userRepo.save(user);
    }

    public AuthReqRes authenticateUser(UserDTO userDTO) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUserName(), userDTO.getPassword()));

        if (authentication.isAuthenticated()) {
            String accessToken = jwtUtil.generateJwtAccessToken(userDTO.getUserName());
            String refreshToken = jwtUtil.generateJwtRefreshToken(userDTO.getUserName());

            return new AuthReqRes(accessToken, refreshToken);
        }
        throw new RuntimeException("Invalid credentials");
    }

    public AuthReqRes refreshJwtToken(AuthReqRes authReqRes) {

        System.out.println("in refresh service");
        String oldJwtToken = authReqRes.getAccessToken();
        String refreshToken = authReqRes.getRefreshToken();

        System.out.println(oldJwtToken);
        System.out.println(refreshToken);

        jwtUtil.setType("refresh");
        String userId = jwtUtil.extractUsername(refreshToken);
//        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        boolean isRefreshTokenValid = jwtUtil.validateToken(refreshToken);

        if (isRefreshTokenValid) {
            String newAccessToken = jwtUtil.generateJwtAccessToken(userId);
            return new AuthReqRes(newAccessToken, refreshToken);
        }
        return null;
    }
}