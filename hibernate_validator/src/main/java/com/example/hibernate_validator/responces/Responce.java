package com.example.hibernate_validator.responces;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Responce {

    private String message;
    private Map<String,String> exception;
    private int statusCode;

}
