package com.example.security_service.service;

import com.example.security_service.dto.UserDto;
import com.example.security_service.entity.Roles;
import com.example.security_service.entity.Users;
import com.example.security_service.exception.InvalidCredentialsException;
import com.example.security_service.exception.InvalidRolesException;
import com.example.security_service.exception.InvalidTokenException;
import com.example.security_service.exception.UserAlreadyPresent;
import com.example.security_service.model.LogIdPassword;
import com.example.security_service.repository.RoleRepository;
import com.example.security_service.repository.UserRepository;
import com.example.security_service.response.JwtResponse;
import com.example.security_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public UserDto registerNewUser(UserDto userDto) {

        Optional<Users> dbUser = userRepository.findById(userDto.getUserId());

        if (dbUser.isPresent()){
            throw new UserAlreadyPresent("User is already registered");
        }

        Users users = new Users();
        users.setUserId(userDto.getUserId());
        users.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        Set<String> roles = userDto.getRoles();
        Set<Roles> rolesInDb = roleRepository.findByRoleIn(roles);

         if (rolesInDb.size() != roles.size()) {
            throw new InvalidRolesException("Given role is invalid");
        }

        users.setRoles(rolesInDb);

        Users savedUsers = userRepository.save(users);
        return new UserDto(savedUsers.getUserId(), savedUsers.getPassword(), savedUsers.getRoles().stream().map(role -> role.getRole()).collect(Collectors.toSet()));
    }

    public JwtResponse login(@RequestBody LogIdPassword logIdPassword){

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logIdPassword.getUserId(), logIdPassword.getPassword()));
        if (!authenticate.isAuthenticated()){
            throw new InvalidCredentialsException("Invalid credentials");
        }

        jwtUtil.setType("access");
        String accessToken = jwtUtil.generateJwtAccessToken(logIdPassword.getUserId());
        String refreshToken = jwtUtil.generateJwtRefreshToken(logIdPassword.getUserId());

        return new JwtResponse(accessToken,refreshToken);
    }

    public JwtResponse refreshToken(String refreshToken) {

        if (!jwtUtil.validateToken(refreshToken)) {
            throw new InvalidTokenException("Invalid refresh token");
        }

        String userId = jwtUtil.extractUsername(refreshToken);
        String accessToken = jwtUtil.generateJwtAccessToken(userId);

        return new JwtResponse(accessToken,refreshToken);
    }
}
