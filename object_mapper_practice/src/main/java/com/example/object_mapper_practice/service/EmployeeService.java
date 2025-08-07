package com.example.object_mapper_practice.service;

import com.example.object_mapper_practice.entity.Employee;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmployeeService {

    @SneakyThrows
    public Employee addEmployee(Employee employee){

        String path = "C:\\Users\\Sreenivas Bandaru\\Documents\\Object_Mapper\\employee app\\employee.txt";
        File employeeFile = new File(path);
        ObjectMapper objectMapper = new ObjectMapper();

        Employee employee2 = objectMapper.readValue(employeeFile, Employee.class);

        JsonNode jsonNode = objectMapper.readTree(employeeFile);
        System.out.println("jsonNode : " + jsonNode);

        Employee employee1 = objectMapper.treeToValue(jsonNode, Employee.class);

        String prettyString = jsonNode.toPrettyString();
        System.out.println("prettyString : " + prettyString);
//        PrettyPrinter prettyPrinter = new MinimalPrettyPrinter();
//        objectMapper.setDefaultPrettyPrinter(prettyPrinter);


    }
}
