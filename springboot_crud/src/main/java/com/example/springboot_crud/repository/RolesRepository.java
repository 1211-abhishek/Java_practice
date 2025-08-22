package com.example.springboot_crud.repository;

import com.example.springboot_crud.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Integer> {

}
