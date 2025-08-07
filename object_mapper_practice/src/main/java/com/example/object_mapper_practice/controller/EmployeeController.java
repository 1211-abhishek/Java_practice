package com.example.object_mapper_practice.controller;

import com.example.object_mapper_practice.entity.Employee;
import com.example.object_mapper_practice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/")
    public Employee addEmployee(@RequestBody Employee employee){

        return employeeService.addEmployee(employee);
    }

}
