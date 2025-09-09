package com.example.design_patterns;

import com.example.design_patterns.abstractfactorybean.Bike;
import com.example.design_patterns.factorybean.Car;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DesignPatternsApplication {

	public static void main(String[] args) {
        ConfigurableApplicationContext cac = SpringApplication.run(DesignPatternsApplication.class, args);

        Car car = cac.getBean(Car.class);
        Car car2 = cac.getBean(Car.class);
        System.out.println(car);
        System.out.println(car);

        System.out.println(car == car2);

        System.out.println(car.hashCode());
        System.out.println(car2.hashCode());

        Bike bike1 = cac.getBean(Bike.class);
        Bike bike2 = (Bike) cac.getBean("bikeFactoryBean");

        System.out.println(bike1);
        System.out.println(bike2);

        System.out.println(bike1 == bike2);
//
//        System.out.println(bike1.hashCode());
//        System.out.println(bike1.hashCode());

    }
}
