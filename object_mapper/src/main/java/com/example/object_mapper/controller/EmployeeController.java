package com.example.object_mapper.controller;

import com.example.object_mapper.service.EmployeeService;
import com.example.object_mapper.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/")
    public Employee addEmployee(@RequestBody String employeeString) {

        System.out.println(employeeString);

        ObjectMapper objectMapper = new ObjectMapper();
        Employee employee;
        try {

            employee = objectMapper.readValue(employeeString, Employee.class);
            System.out.println(employee);


            objectMapper.writeValue(new File("C:\\Users\\Sreenivas Bandaru\\Documents\\Object_Mapper\\String.txt"), employeeString);
            objectMapper.writeValue(new File("C:\\Users\\Sreenivas Bandaru\\Documents\\Object_Mapper\\object.txt"), employee);

            System.out.println("------------ reading file -----------");
            Employee employeeFromFile = objectMapper.readValue(new File("C:\\Users\\Sreenivas Bandaru\\Documents\\Object_Mapper\\object.txt"), Employee.class);
            System.out.println(employeeFromFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return employeeService.addEmployee(employee);
    }


}
