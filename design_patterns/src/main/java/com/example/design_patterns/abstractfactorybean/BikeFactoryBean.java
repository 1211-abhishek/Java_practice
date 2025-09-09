package com.example.design_patterns.abstractfactorybean;

import lombok.Setter;
import org.springframework.beans.factory.config.AbstractFactoryBean;

@Setter
public class BikeFactoryBean extends AbstractFactoryBean<Bike> {

    private String bikeName;
    private String brandName;
    private double mileage;

    @Override
    public Class<?> getObjectType() {
        return Bike.class;
    }

    @Override
    protected Bike createInstance() throws Exception {
        return new Bike(bikeName,brandName,mileage);
    }
}
