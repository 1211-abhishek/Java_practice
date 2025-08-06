package com.example.multiple_databases.entity.mysql;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class MysqlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mysqlId;

    private String name;
}
