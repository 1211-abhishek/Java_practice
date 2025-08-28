package com.example.email_service_task.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.mail.imap")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IMAPConfig {

    private String host;
    private String port;
    private String user;
    private String password;
    private String protocol;
    private String sslEnable;
}
