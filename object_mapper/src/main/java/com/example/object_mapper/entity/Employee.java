package com.example.object_mapper.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    @SequenceGenerator(name = "empId_generator", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empId_generator")
    private int empId;

    private String empName;

    @OneToOne
    private Car car;

}
