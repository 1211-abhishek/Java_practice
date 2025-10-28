package com.example.webclient_practice.controller;

import com.example.webclient_practice.model.Post;
import com.example.webclient_practice.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/web")
public class Controllers {

    @Autowired
    private Services services;

    @GetMapping("/")
    public ResponseEntity<Flux<Post>> getAllPosts(){

        Flux<Post> allPosts = services.getAllPosts();
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Post>> getPostById(@PathVariable int id){

        Mono<Post> postById = services.getPostById(id);
        return new ResponseEntity<>(postById, HttpStatus.OK);
    }
}
