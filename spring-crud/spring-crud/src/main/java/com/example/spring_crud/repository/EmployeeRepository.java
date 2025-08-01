package com.example.spring_crud.repository;

import com.example.spring_crud.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

//    @Transactional
//    public List<Employee> findAllByEmpName(String empName);

//@Transactional
//public void deleteByEmpName(String empName);

//    @Transactional
//    //@Modifying
//    @Query("DELETE FROM Employee e WHERE e.empName = :empName")
//    void deleteByEmpName(String empName);
//
    @Transactional
    @Modifying
    @Query("INSERT INTO Employee (empName,department) VALUES (:empName,:department)")
    int insertEmployeeByQuery( String empName, String department);
   // Employee insertEmployeeByQuery( String empName, String department);

}

