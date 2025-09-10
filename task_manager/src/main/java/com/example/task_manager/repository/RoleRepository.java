package com.example.task_manager.repository;

import com.example.task_manager.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Integer> {


    Optional<Roles> findByRole(String name);
}
