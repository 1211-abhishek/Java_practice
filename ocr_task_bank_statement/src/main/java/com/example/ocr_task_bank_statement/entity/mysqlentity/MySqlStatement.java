package com.example.ocr_task_bank_statement.entity.mysqlentity;

import com.example.ocr_task_bank_statement.model.TransactionType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MySqlStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long statementId;

    private int accountNumber;

    private LocalDate transactionDate;

    private double transactionAmount;

    private TransactionType transactionType;

    private double currentBalance;
}
