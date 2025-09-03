package com.example.design_patterns.decorator;

public class SimpleTea implements Tea{

    @Override
    public String getTeaType() {
        return "Tea";
    }

    @Override
    public double getPrice() {
        return 10;
    }
}
