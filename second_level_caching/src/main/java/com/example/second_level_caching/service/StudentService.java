package com.example.second_level_caching.service;

import com.example.second_level_caching.entity.StudentEntity;
import com.example.second_level_caching.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public StudentEntity getStudent(int id){

        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
    }
}

