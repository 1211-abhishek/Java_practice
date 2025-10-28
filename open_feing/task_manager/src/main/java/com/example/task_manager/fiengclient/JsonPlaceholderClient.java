package com.example.task_manager.fiengclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "jsonPlaceholder", url = "https://jsonplaceholder.typicode.com")
public interface JsonPlaceholderClient {

    @GetMapping("/users/{id}")
    User getUser(@PathVariable("id") Long id);

    @GetMapping("/users")
    List<User> getAllUsers();
}