package com.example.design_patterns.decorator;

public class TeaDecorator implements Tea{

    public Tea tea;

    public TeaDecorator(Tea simpleTea){
        this.tea = simpleTea;
    }

    @Override
    public String getTeaType() {
        return tea.getTeaType();
    }

    @Override
    public double getPrice() {
        return tea.getPrice();
    }
}
