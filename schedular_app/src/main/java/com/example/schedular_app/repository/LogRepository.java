package com.example.schedular_app.repository;

import com.example.schedular_app.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogEntity,Long> {


}
