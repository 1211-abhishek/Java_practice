package com.example.spring_security_blog_app.exceptions.securit;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(TestException.class)
//    public String exception(TestException testException){
//
//        System.out.println("In exception handler : " + testException.getMessage());
//        return testException.getMessage();
//    }
}
