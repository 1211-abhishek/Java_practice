package com.example.second_level_caching.controller;

import com.example.second_level_caching.entity.StudentEntity;
import com.example.second_level_caching.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/")
    public StudentEntity getStudent(@RequestParam int id){

        return studentService.getStudent(id);
    }
}
