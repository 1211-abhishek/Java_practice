package com.example.spring_crud.service;

import com.example.spring_crud.exceptions.EmployeeAlreadyExistsException;
import com.example.spring_crud.exceptions.EmployeeNotFoundException;
import com.example.spring_crud.model.Employee;
import com.example.spring_crud.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getEmployee(int empId) {
        //log.info();
        return employeeRepository.findById(empId).orElseThrow(() -> {
            log.error("Employee with empId {} not found", empId);
            return new EmployeeNotFoundException("Employee with empId " + empId + " not found");
        });
    }

    public Employee postEmployee(Employee employee) {
        System.out.println(employee.getEmpId());
        if (employeeRepository.findById(employee.getEmpId()).isEmpty()) {


            System.out.println("if block");
            System.out.println(employee);
            return employeeRepository.save(employee);
        } else {
            System.out.println("else block");
            log.error(String.format("Employee with empId %s is already exist", employee.getEmpId()));
            throw new EmployeeAlreadyExistsException("Employee with empId " + employee.getEmpId() + " already exists");
        }
    }

    public Employee putEmployee(Employee employee) {
        Employee existingEmp = employeeRepository.findById(employee.getEmpId())
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with empId " + employee.getEmpId() + " not found"));

        if (employee.getEmpName() != null) {
            existingEmp.setEmpName(employee.getEmpName());
        }
        if (employee.getDepartment() != null) {
            existingEmp.setDepartment(employee.getDepartment());
        }

        return employeeRepository.save(existingEmp);
    }


    public boolean deleteEmployee(int empId) {

        Optional<Employee> employee = employeeRepository.findById(empId);
        if (employee.isPresent()) {

            employeeRepository.deleteById(empId);
            return true;
        } else {
            log.error(String.format("Employee with empId %s not found", empId));
            throw new EmployeeNotFoundException("Employee with empId " + empId + " not found");
        }
    }

    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "empId"));
    }

    public List<Employee> getAllEmployeesByEmpName(String empName) {

//        return employeeRepository.findAllByEmpName(empName);
        return  null;
    }

    public void deleteEmployeeByName(String empName) {

//        employeeRepository.deleteByEmpName(empName);
    }


    public int postEmployeeByQuery(String empName, String department) {

        return employeeRepository.insertEmployeeByQuery(empName, department);
    }
}
