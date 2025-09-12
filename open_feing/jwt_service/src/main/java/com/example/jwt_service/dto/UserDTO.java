package com.example.jwt_service.dto;

import com.example.jwt_service.entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

//    private int userId;
    private String userName;
    private String password;
    private Set<String> roles = new HashSet<>();
}
