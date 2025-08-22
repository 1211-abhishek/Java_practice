package com.example.springboot_crud.constants;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "appdetails")
public class AppConstants {

    public String appName;
    public String appVersion;
}
