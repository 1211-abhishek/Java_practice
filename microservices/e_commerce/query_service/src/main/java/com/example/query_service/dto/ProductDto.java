package com.example.query_service.dto;

import com.example.query_service.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private int productId;

    private String productName;
    private String description;
    private double price;
    private int quantity;
    private Users seller;

}
