package com.example.spring_crud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "EidikoEmployee", indexes ={
//        @Index(name = "nameIndex", columnList = "empName")
//})
public class Employee {

    @Id
    @SequenceGenerator(name = "name1", allocationSize = 50, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "name1")
    // @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int empId;
    private String empName;
    private String department;
}
