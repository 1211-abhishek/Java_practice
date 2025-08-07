package com.example.object_mapper.service;

import com.example.object_mapper.repository.EmployeeRepository;
import com.example.object_mapper.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }
}
