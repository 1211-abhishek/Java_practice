package com.example.ocr_task_bank_statement.exception;

public class InvalidFileTypeException extends RuntimeException {
  public InvalidFileTypeException(String message) {
    super(message);
  }
}
