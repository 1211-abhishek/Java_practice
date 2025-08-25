package com.example.hibernate_validator.exceptions;

import com.example.hibernate_validator.responces.Responce;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity validationException(MethodArgumentNotValidException methodArgumentNotValidException){

        String message = methodArgumentNotValidException.getMessage();

        Map<String, String> errors = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(error -> {

            errors.put(error.getField(), error.getDefaultMessage());
        });

        System.out.println(message);

        return new ResponseEntity(new Responce("Bad request", errors, 400), HttpStatus.BAD_REQUEST);
    }
}
