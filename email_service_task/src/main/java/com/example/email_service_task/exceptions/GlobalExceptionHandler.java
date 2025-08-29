package com.example.email_service_task.exceptions;

import com.example.email_service_task.responces.ExceptionResponce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FailedToSendEmailException.class)
    public ResponseEntity<ExceptionResponce> failedToSendEmail(FailedToSendEmailException failedToSendEmailException){

        ExceptionResponce failedToSendEmail = new ExceptionResponce("Failed to send email", HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, failedToSendEmailException.getMessage());
        log.error(failedToSendEmail.toString());
        return new ResponseEntity<>(failedToSendEmail,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SomethingWentWrongException.class)
    public ResponseEntity<ExceptionResponce> somethingWentWrong(SomethingWentWrongException somethingWentWrong){

        ExceptionResponce somethingWentWrongResponse = new ExceptionResponce("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, somethingWentWrong.getMessage());
        log.error(somethingWentWrongResponse.toString());
        return new ResponseEntity<>(somethingWentWrongResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
