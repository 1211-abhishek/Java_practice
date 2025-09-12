package com.example.jwt_service.controller;

import com.example.jwt_service.dto.AuthReqRes;
import com.example.jwt_service.dto.UserDTO;
import com.example.jwt_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public AuthReqRes logInUser(@RequestBody UserDTO userDTO){

        return userService.authenticateUser(userDTO);
    }

    @PostMapping("/refresh")
    public AuthReqRes refreshJwtToken(@RequestBody AuthReqRes authReqRes){

        return userService.refreshJwtToken(authReqRes);
    }

//    @PostMapping("/validate")
//    public Authentication validate(@RequestBody String token){
//
//        return userService.validate(token);
//    }
}
