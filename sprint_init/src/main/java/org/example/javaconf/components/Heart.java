package org.example.javaconf.components;

import org.example.javaconf.Interfaces.BodyPart;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Heart implements BodyPart {

    String organName = "heart";

    public Heart() {
        System.out.println("In Heart constructor");
    }

    @Override
    public void function() {
        System.out.println("Pump blood");
    }

    @Override
    public String toString() {
        return "Heart{" +
                "organName='" + organName + '\'' +
                '}';
    }
}
