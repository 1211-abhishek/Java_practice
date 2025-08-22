package com.example.springboot_crud.config;

import com.example.springboot_crud.constants.AppConstants;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Autowired
    public AppConstants appConstants;

    @Bean
    public OpenAPI configSwagger() {

        System.out.println(appConstants.appVersion);
        return new OpenAPI().info(new Info().
                title("Config swagger title").
                summary("Config swagger summary").
                //version(appConstants.appVersion).
                version(appConstants.appVersion).
                description("Config swagger description"));
    }

}
