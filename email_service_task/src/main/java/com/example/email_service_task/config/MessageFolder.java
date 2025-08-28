package com.example.email_service_task.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "message")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageFolder {

    List<String> folder;
}
