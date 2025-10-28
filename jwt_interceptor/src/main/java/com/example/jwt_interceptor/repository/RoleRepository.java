package com.example.jwt_interceptor.repository;

import com.example.jwt_interceptor.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Roles, Long> {
    Set<Roles> findByRoleIn(Set<String> roles);
}
