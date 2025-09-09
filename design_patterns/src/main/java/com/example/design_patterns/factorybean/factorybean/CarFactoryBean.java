package com.example.design_patterns.factorybean.factorybean;

import com.example.design_patterns.factorybean.Car;
import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;

@Setter
public class CarFactoryBean implements FactoryBean<Car> {

    private String carName;
    private String brandName;
    private double mileage;

    @Override
    public Car getObject() throws Exception {
        return new Car(carName,brandName,mileage);
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
//        return FactoryBean.super.isSingleton();
    }
}
