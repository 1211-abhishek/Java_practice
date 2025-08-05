package com.example.spring_mapping.repository;


import com.example.spring_mapping.entity.Players;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Players, Integer> {


}
