package com.example.springboot_crud.repository;

import com.example.springboot_crud.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users,Integer> {
}
