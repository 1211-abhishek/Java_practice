package com.example.query_service.services;

import com.example.query_service.dto.ProductDto;
import com.example.query_service.entity.Product;
import com.example.query_service.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    public ProductDto addProduct(ProductDto productDto){

        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setSeller(productDto.getSeller());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setQuantity(productDto.getQuantity());

        Product savedProduct = productRepository.save(product);

        return objectMapper.convertValue(savedProduct, ProductDto.class);
    }
}
