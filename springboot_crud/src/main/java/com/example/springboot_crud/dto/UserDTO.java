package com.example.springboot_crud.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDTO {


    private int userId;

    @Email(message = "Email should be valid")
    private String email;

    private String passwordHash;

    @NotEmpty
    private String fullName;

    @Pattern(regexp = "\\d{10,12}", message = "Invalid Phone number, Phone must be digits only and not exceed 15 characters")
    private String phone;


    private String avatarURL;
}
