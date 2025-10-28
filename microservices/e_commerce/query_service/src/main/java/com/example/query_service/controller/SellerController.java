package com.example.query_service.controller;

import com.example.query_service.dto.ProductDto;
import com.example.query_service.services.SellerService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping("/add-product")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto){


    }
}
