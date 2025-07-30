package com.example.spring_crud.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ExceptionMessage {

    String exceptionMessage;
    String userMessage;
    int statusCode;

}
