package com.example.kafka_consumer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeModel {

    private int employeeId;
    private String employeeName;
}
