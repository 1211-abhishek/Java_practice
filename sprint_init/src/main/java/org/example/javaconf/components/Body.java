package org.example.javaconf.components;

import org.example.javaconf.Interfaces.BodyPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Body {

    boolean isAlive = true;
    @Autowired
    Heart heart;
    Lungs lungs;

//    public Body() {
//        System.out.println("In Body Constructor");
//    }

    public Body(@Value("True") boolean isAlive, Heart heart, Lungs lungs) {
        this.isAlive = isAlive;
        this.heart = heart;
        this.lungs = lungs;
    }


    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public BodyPart getHeart() {
        return heart;
    }

    public void setHeart(Heart heart) {
        System.out.println("Setter getHeart");
        this.heart = heart;
    }

    public Lungs getLungs() {
        return lungs;
    }

    @Autowired
    public void setLungs(Lungs lungs) {
        this.lungs = lungs;
    }

    @Override
    public String toString() {
        return "Body{" +
                "isAlive=" + isAlive +
                ", heart=" + heart +
                ", lungs=" + lungs +
                '}';
    }
}
