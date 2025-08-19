package com.example.ocr_task_bank_statement.entity.oracleentity;

import com.example.ocr_task_bank_statement.model.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OracleStatement {

    @Id
    @SequenceGenerator(name = "statement_id_generator", allocationSize = 10,initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "statement_id_generator")
    private int statementId;

    private int accountNumber;

    private Date transactionDate;

    private double transactionAmount;

    private TransactionType transactionType;

    private double currentBalance;
}
