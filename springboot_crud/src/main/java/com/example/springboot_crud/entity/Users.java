package com.example.springboot_crud.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Users {

    @Id
    @SequenceGenerator(name = "userIdGenerator",initialValue = 100,allocationSize = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int userId;

    @Column(name = "EMAIL",nullable = false)
    @Email(message = "Email should be valid")
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    @NotEmpty
    private String fullName;

    @Column(nullable = false)
    @Pattern(regexp = "\\d{10,12}", message = "Invalid Phone number, Phone must be digits only and not exceed 15 characters")
    private String phone;

    private String avatarURL;

    @Column(nullable = false)
    private int isDeleted = 0;

    private LocalDateTime deletedAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

//    Validation failed for argument [0] in public org.springframework.http.ResponseEntity<com.example.springboot_crud.responces.SingleUserResponse> com.example.springboot_crud.controller.UserController.postUser(com.example.springboot_crud.entity.Users) with 3 errors: [Field error in object 'users' on field 'phone': rejected value [12346789];
//    codes [Pattern.users.phone,Pattern.phone,Pattern.java.lang.String,Pattern];
//    arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [users.phone,phone];
//    arguments [];
//    default message [phone],[Ljakarta.validation.constraints.Pattern$Flag;@788b3f8c,\\d{10,12}];
//    default message [Phone must be digits only and not exceed 15 characters]][Field error in object 'users' on field 'email': rejected value [@l.m];
//    codes [Size.users.email,Size.email,Size.java.lang.String,Size];
//    arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [users.email,email];
//    arguments [];
//    default message [email],2147483647,5];
//    default message [Email must be at least 5 characters long]] [Field error in object 'users' on field 'email': rejected value [@l.m];
//    codes [Email.users.email,Email.email,Email.java.lang.String,Email];
//    arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [users.email,email];
//    arguments [];
//    default message [email],[Ljakarta.validation.constraints.Pattern$Flag;@25559833,.*];
//    default message [Email should be valid]]

}
