package com.example.spring_security_blog_app.repository;

import com.example.spring_security_blog_app.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
}
