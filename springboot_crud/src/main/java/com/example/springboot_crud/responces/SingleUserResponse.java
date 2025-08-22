package com.example.springboot_crud.responces;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SingleUserResponse {

    private final int userId;
    private String email;
    private String fullName;
    private String phone;
    private String avatarUrl;
}
