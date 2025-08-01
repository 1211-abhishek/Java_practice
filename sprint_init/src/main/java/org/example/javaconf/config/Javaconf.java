package org.example.javaconf.config;

import jakarta.annotation.*;
import org.example.javaconf.Interfaces.BodyPart;
import org.example.javaconf.components.Body;
import org.example.javaconf.components.Heart;
import org.example.javaconf.components.Lungs;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("org.example.javaconf")
public class Javaconf {

    public Javaconf() {
        System.out.println("In Java conf constructor");
    }


//    @Bean(initMethod = "init" , destroyMethod = "destroy")
//    public BodyPart heart(){
//        return new Heart();
//    }

    @PreDestroy
    public void dispose() throws Exception {
        System.out.println("Beans are being destroyed");
    }

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        System.out.println("Beans are created");

    }

    @Bean
    public Heart heart() {
        return new Heart();
    }

    @Bean
    public Lungs lungs() {
        return new Lungs();
    }

    @Bean
    public Body body() {
        return new Body(true,heart(),lungs());
    }
}
