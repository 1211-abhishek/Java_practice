package com.example.email_service_task.responces;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponce {

    private String message;
    private int statusCode;
    private HttpStatus httpStatus;
    private String exception;
}
