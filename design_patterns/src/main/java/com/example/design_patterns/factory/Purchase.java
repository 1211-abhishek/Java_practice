package com.example.design_patterns.factory;

public class Purchase {

    public static void main(String[] args) {

        Payment payment = PymentProcessorFactory.processPaymentBy("card");
        payment.processPayment();

        payment = PymentProcessorFactory.processPaymentBy("UPI");
        payment.processPayment();

    }
}
