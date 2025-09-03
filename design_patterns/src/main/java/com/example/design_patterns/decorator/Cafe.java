package com.example.design_patterns.decorator;

public class Cafe {

    public static void main(String[] args) {

        Tea tea = new SimpleTea();

        tea = new LemonTea(tea);
        System.out.println(tea.getTeaType());
        System.out.println(tea.getPrice());

        tea = new MasalaTea(tea);
        System.out.println(tea.getTeaType());
        System.out.println(tea.getPrice());
    }
}
