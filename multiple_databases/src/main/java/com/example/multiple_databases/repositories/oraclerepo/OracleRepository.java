package com.example.multiple_databases.repositories.oraclerepo;

import com.example.multiple_databases.entity.oraclesql.OracleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OracleRepository extends JpaRepository<OracleEntity, Integer> {
}
