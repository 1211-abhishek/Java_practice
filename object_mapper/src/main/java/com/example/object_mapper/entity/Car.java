package com.example.object_mapper.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Car {

    @Id
    private int carId;

    private String carName;

   // private CarBrand carBrand;
}
