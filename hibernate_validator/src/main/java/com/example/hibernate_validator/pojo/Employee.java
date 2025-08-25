package com.example.hibernate_validator.pojo;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @NotNull
    Integer employeeId;

    @Email
    String emailId;

    @NotNull
    private Double salary;

    @Digits(integer = 12, message = "mobile number cant exceed 10 digits", fraction = 0)
    private String mobileNumber;
}
