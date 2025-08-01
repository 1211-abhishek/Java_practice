package com.example.spring_crud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ExceptionMessage> employeeNotFoundException(EmployeeNotFoundException employeeNotFoundException){

        return new ResponseEntity<>(new ExceptionMessage("Employee Not Found", employeeNotFoundException.getMessage(), HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    public ResponseEntity<ExceptionMessage> employeeAlreadyExistException(EmployeeAlreadyExistsException employeeAlreadyExistsException){

        return new ResponseEntity<>(new ExceptionMessage("Employee Already Exist", employeeAlreadyExistsException.getMessage(), HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT);
    }


}
