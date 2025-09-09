package com.example.second_level_caching.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.Data;

@Data
@Entity
@Table(name = "students")
//@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String studentName;
    private String emailId;
}
