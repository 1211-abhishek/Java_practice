package com.example.task_manager.controller;


import com.example.task_manager.dto.UserDTO;
import com.example.task_manager.entity.Users;
import com.example.task_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String logInUser(@RequestBody UserDTO userDTO){

        return userService.authenticateUser(userDTO);
    }
    @PostMapping("/register")
    public ResponseEntity<UserDTO> postUser(@RequestBody UserDTO userDTO){

        Users users = userService.postUser(userDTO);
        return new ResponseEntity<>(new UserDTO(users.getUserName(), users.getPassword(), users.getRoles().stream().map(r -> r.getRole()).collect(Collectors.toSet())), HttpStatus.OK);
    }
}
