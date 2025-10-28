package com.example.security_service.repository;

import com.example.security_service.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, String> {
}
