package com.example.spring_crud.controller;

import com.example.spring_crud.model.Employee;
import com.example.spring_crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{empId}")
    public Employee getEmployee(@PathVariable int empId) {
        return employeeService.getEmployee(empId);
    }

    @GetMapping("/by-name")
    public List<Employee> getAllEmployeeByEmpName(@RequestParam String empName) {
        return employeeService.getAllEmployeesByEmpName(empName);
    }

    @PostMapping("/")
    public Employee postEmployee(@RequestBody Employee emp) {

        return employeeService.postEmployee(emp);
    }

    @PostMapping("/by-query")
    public int postEmployeeByQuery(@RequestBody Employee emp) {

         return employeeService.postEmployeeByQuery(emp.getEmpName(),emp.getDepartment());

    }

    @DeleteMapping("/{empId}")
    public String deleteEmployee(@PathVariable int empId) {

        if (employeeService.deleteEmployee(empId)) {
            return "Successfully deleted employee with empId " + empId;
        }
        return "Cant delete emp with id " + empId;

    }

    @DeleteMapping("/by-name")
    public String deleteEmployee(@RequestParam String empName) {

        employeeService.deleteEmployeeByName(empName);
        return "Deleted";
    }

    @PutMapping("/")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.putEmployee(employee);
    }
}
