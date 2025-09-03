package com.example.design_patterns.factory;

public class Cardayment implements Payment{

    @Override
    public void processPayment() {
        System.out.println("Processing Card payment");
    }
}
