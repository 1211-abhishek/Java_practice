package com.example.spring_crud.exceptions;

public class EmployeeNotFoundException extends  RuntimeException{

    public  EmployeeNotFoundException(String message){
        super(message);
    }
}
