package com.example.spring_mapping.repository;

import com.example.spring_mapping.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {
}
