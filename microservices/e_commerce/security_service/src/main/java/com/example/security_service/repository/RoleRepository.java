package com.example.security_service.repository;

import com.example.security_service.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Set<Roles> findByRoleIn(Set<String> roles);
}
