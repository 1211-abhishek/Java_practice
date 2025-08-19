package com.example.ocr_task_bank_statement.repository.oraclerepo;

import com.example.ocr_task_bank_statement.entity.oracleentity.OracleStatement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OracleRepository extends JpaRepository<OracleStatement, Long> {
}
