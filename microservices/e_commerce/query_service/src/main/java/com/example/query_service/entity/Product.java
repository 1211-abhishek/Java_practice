package com.example.query_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int productId;

    private String productName;
    private String description;
    private double price;
    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    private Users seller;
}
