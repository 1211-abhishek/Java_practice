package com.example.tesseract.repository;

import com.example.tesseract.entity.Aadhar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OCRRepository extends JpaRepository<Aadhar, Integer> {
}
