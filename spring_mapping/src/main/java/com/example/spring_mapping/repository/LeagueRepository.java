package com.example.spring_mapping.repository;

import com.example.spring_mapping.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueRepository extends JpaRepository<League,Integer> {
}
