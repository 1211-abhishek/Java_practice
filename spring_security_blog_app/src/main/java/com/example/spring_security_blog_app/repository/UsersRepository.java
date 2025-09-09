package com.example.spring_security_blog_app.repository;

import com.example.spring_security_blog_app.entity.BlogUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<BlogUsers, Integer> {

    BlogUsers findByUserName(String username);
}
