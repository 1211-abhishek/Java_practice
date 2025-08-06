package com.example.multiple_databases.entity.oraclesql;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OracleEntity {

    @Id
    @SequenceGenerator(name = "oracle_id_generator", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "oracle_id_generator")
    private int oracleId;

    private String name;
}
