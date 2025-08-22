package com.example.ocr_checkbook.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChequeInfo {
    private final String payeeName;
    private final String amountInWords;
    private final String accountNumber;

    @Override
    public String toString() {
        return "Payee Name: " + payeeName + "\n" +
                "amountInWords: " + amountInWords + "\n" +
                "Account Number: " + accountNumber;
    }
}
