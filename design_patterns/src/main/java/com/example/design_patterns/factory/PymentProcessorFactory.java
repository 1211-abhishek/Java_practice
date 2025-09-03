package com.example.design_patterns.factory;

public class PymentProcessorFactory {

    public static Payment processPaymentBy(String paymentType) {

        if (paymentType.equalsIgnoreCase("card")) {
            return new Cardayment();
        } else if (paymentType.equalsIgnoreCase("UPI")) {
            return new UPIPayment();
        } else {
            throw new RuntimeException("Invalid payment type");
        }
    }
}
