package com.example.task_manager.fiengclient.errors;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignErrorConfig {

    @Bean
    public ErrorDecoder customErrorDecoder() {
        return (methodKey, response) -> {
            System.out.println("Method key : " + methodKey);
            if (response.status() == 404) {
                return new RuntimeException("custom : Resource not found!");
            }
            return new RuntimeException("custom : Generic error: " + response.status());
        };
    }
}
