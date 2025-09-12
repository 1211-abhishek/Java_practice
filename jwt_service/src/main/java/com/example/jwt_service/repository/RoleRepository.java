package com.example.jwt_service.repository;

import com.example.jwt_service.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Integer> {


    Optional<Roles> findByRole(String name);
}
