package com.example.design_patterns.decorator;

public class MasalaTea extends TeaDecorator {

    public MasalaTea(Tea simpleTea) {
        super(simpleTea);
    }

    @Override
    public String getTeaType() {
        return super.getTeaType() + " With masala";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 10;
    }
}
