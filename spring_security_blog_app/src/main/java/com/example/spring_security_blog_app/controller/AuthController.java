package com.example.spring_security_blog_app.controller;

import com.example.spring_security_blog_app.entity.BlogUsers;
import com.example.spring_security_blog_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> validateUser(@RequestBody BlogUsers blogUsers){

        System.out.println("inside controller...");

        String token = userService.authenticateUser(blogUsers);
        return new ResponseEntity<>(token, HttpStatus.OK);
//        return new ResponseEntity<>("ss", HttpStatus.OK);
    }
}
