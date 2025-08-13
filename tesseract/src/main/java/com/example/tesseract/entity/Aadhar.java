package com.example.tesseract.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aadhar {

    @Id
    private String aadharId;

    private String name;

    private String dob;

    private String gender;



}
