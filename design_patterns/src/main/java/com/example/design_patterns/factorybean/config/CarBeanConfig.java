package com.example.design_patterns.factorybean.config;

import com.example.design_patterns.factorybean.factorybean.CarFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarBeanConfig {

    @Bean
    public CarFactoryBean carFactoryBean(){
        CarFactoryBean carFactoryBean = new CarFactoryBean();
        carFactoryBean.setCarName("Defender");
        carFactoryBean.setBrandName("LR");
        carFactoryBean.setMileage(10);
        return carFactoryBean;
    }
}
