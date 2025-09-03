package com.example.design_patterns.iterator;

import java.util.ArrayList;
import java.util.List;

public class MyArray implements MakeIterator{

    String[] str = {"a", "b", "c", "d"};

    @Override
    public Iterator getIterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator {

        public int index = 0;

        @Override
        public boolean hasNext() {
            return index < str.length;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                return str[index++];
            }
            throw new  RuntimeException("Array cant further iterable");
        }
    }
}
