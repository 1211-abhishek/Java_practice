package com.example.spring_mapping.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteResponse {

    String status;
    String message;
}
