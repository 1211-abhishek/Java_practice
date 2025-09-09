package com.example.design_patterns.abstractfactorybean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bike {

    private String bikeName;
    private String brandName;
    private double mileage;
}
