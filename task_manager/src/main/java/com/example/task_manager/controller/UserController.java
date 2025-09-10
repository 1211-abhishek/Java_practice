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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    public ResponseEntity<List<UserDTO>> getAllUsers(){

        List<Users> allUsers = userService.getAllUsers();

        List<UserDTO> userDTOList = new ArrayList<>();
        for (Users users : allUsers) {
            userDTOList.add(new UserDTO(users.getUserName(), users.getPassword(), users.getRoles().stream().map(r-> r.getRole()).collect(Collectors.toSet())));
        }

        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }


}
