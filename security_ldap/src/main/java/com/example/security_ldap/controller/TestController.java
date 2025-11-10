package com.example.security_ldap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String home() {
        return "Welcome to Home!";
    }

    @GetMapping("/developer")
    public String developer() {
        return "Hello Developer!";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hello Admin!";
    }
}
