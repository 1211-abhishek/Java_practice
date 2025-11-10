package com.example.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RabbitAmqpTutorialsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitAmqpTutorialsApplication.class, args);
    }
}