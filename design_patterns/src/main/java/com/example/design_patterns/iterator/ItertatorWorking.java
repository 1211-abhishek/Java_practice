package com.example.design_patterns.iterator;

public class ItertatorWorking {

    public static void main(String[] args) {

        MyArray myArray = new MyArray();

        Iterator iterator = myArray.getIterator();

        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
    }
}
