package com.example.spring_security_blog_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int userId;

    private String userName;

    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )    private Set<Roles> roles = new HashSet<>();
}
