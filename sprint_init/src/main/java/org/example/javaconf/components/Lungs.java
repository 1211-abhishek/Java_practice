package org.example.javaconf.components;

import org.example.javaconf.Interfaces.BodyPart;
import org.springframework.stereotype.Component;

@Component
public class Lungs implements BodyPart {

    String organName = "lungs";

    public Lungs() {
        System.out.println("In Lungs constructor");
    }

    @Override
    public void function() {
        System.out.println("Breathing ");
    }

    @Override
    public String toString() {
        return "Lungs{" +
                "organName='" + organName + '\'' +
                '}';
    }
}
