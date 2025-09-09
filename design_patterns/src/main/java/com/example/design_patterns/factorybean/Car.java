package com.example.design_patterns.factorybean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private String carName;
    private String brandName;
    private double mileage;
}
