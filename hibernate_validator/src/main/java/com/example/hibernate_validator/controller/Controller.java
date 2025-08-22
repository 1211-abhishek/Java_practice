package com.example.hibernate_validator.controller;

import com.example.hibernate_validator.pojo.Employee;
import jakarta.validation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/validation")
public class Controller {

    @PostMapping
    public void postEmployee(@Valid @RequestBody Employee employee){
        System.out.println(employee);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Employee employee1 = new Employee();
        employee1.setEmailId("ssss");
        employee1.setMobileNumber("12245632179542");
        System.out.println(employee1);

        Set<ConstraintViolation<Employee>> violations = validator.validate(employee1);

        for (ConstraintViolation<Employee> violation : violations) {
            System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
        }
    }
}
