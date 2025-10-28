package com.example.jwt_interceptor.repository;


import com.example.jwt_interceptor.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUserName(String userName);
}
