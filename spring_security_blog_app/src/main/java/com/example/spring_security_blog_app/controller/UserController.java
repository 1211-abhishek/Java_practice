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
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/")
    public BlogUsers postNewUsers(@RequestBody BlogUsers blogUsers){

        return userService.postNewUsers(blogUsers);
    }



//    @PostMapping("/login")
//    public String validateUser(@RequestBody BlogUsers blogUsers){
//
//        return userService.validateUser(blogUsers);
//    }
}
