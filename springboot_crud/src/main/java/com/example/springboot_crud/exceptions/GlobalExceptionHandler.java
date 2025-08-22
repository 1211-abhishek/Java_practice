package com.example.springboot_crud.exceptions;

import com.example.springboot_crud.exceptions.entitymessage.ExceptionEntityMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionEntityMessage> userNotFoundException(UserNotFoundException userNotFoundException) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Exception", userNotFoundException.getMessage());
        ResponseEntity<ExceptionEntityMessage> userNotFound = new ResponseEntity<>(new ExceptionEntityMessage("User not found", errors, HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
        log.error(userNotFound.toString());
        return userNotFound;
    }

    @ExceptionHandler(UserNotUpdatedException.class)
    public ResponseEntity<ExceptionEntityMessage> userNotUpdatedException(UserNotUpdatedException userNotUpdatedException) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Exception", userNotUpdatedException.getMessage());
        ResponseEntity<ExceptionEntityMessage> userNotUpdated = new ResponseEntity<>(new ExceptionEntityMessage("UserDTO not updated", errors, HttpStatus.NOT_MODIFIED.value()), HttpStatus.NOT_MODIFIED);
        log.error(userNotUpdated.toString());
        return userNotUpdated;
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ExceptionEntityMessage> userNotUpdatedException(MethodArgumentNotValidException  methodArgumentNotValidException ) {
//        return new ResponseEntity<>(new ExceptionEntityMessage("Check Body Parameters", methodArgumentNotValidException.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
//    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionEntityMessage> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {

            errors.put(error.getField(), error.getDefaultMessage());
        });
        ResponseEntity<ExceptionEntityMessage> invalidBodyParameters = new ResponseEntity<>(new ExceptionEntityMessage("Invalid Body Parameters", errors, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
        log.error(invalidBodyParameters.toString());
        return invalidBodyParameters;
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ExceptionEntityMessage> handleRoleNotFoundException(RoleNotFoundException roleNotFoundException){
        Map<String, String> errors = new HashMap<>();
        errors.put("Exception", roleNotFoundException.getMessage());
        ResponseEntity<ExceptionEntityMessage> roleNotFound = new ResponseEntity<>(new ExceptionEntityMessage("User Role not found", errors, HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
        log.error(roleNotFound.toString());
        return roleNotFound;
    }
}
