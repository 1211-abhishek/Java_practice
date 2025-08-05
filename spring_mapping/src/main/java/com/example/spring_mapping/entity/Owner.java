package com.example.spring_mapping.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Owner {

    @Id
    @SequenceGenerator(name = "owner_id_generator", allocationSize = 10, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "owner_id_generator")
    private int ownerId;

    //@Column(nullable = false)
    private String ownerName;
}
