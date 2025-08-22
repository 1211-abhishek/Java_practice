package com.example.springboot_crud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Roles {

    @Id
    @SequenceGenerator(name = "role_id_generator", allocationSize = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "role_id_generator")
    private int roleId;

    private String roleName;
}
