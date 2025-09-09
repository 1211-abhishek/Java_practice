package com.example.design_patterns.abstractfactorybean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BikeBeanConfig {

    @Bean
    public BikeFactoryBean bikeFactoryBean() {

        BikeFactoryBean bikeFactoryBean = new BikeFactoryBean();
        bikeFactoryBean.setBikeName("FZX");
        bikeFactoryBean.setBrandName("Yamha");
        bikeFactoryBean.setMileage(20);
        return bikeFactoryBean;
    }
}
