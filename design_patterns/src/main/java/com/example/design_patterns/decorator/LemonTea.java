package com.example.design_patterns.decorator;

public class LemonTea extends TeaDecorator {

    public LemonTea(Tea simpleTea){
        super(simpleTea);
    }

    @Override
    public String getTeaType() {
        return super.getTeaType() + " Lemon";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 5;
    }
}
