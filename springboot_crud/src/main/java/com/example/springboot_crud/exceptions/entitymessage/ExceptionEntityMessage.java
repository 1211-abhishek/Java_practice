package com.example.springboot_crud.exceptions.entitymessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionEntityMessage {

     String generalMessage;
     Map UserMessage;
     int httpStatusCode;
}
