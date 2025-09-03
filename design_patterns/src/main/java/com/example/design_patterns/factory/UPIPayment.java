package com.example.design_patterns.factory;

public class UPIPayment implements Payment{

    @Override
    public void processPayment() {
        System.out.println("Processing UPI payment");
    }
}
