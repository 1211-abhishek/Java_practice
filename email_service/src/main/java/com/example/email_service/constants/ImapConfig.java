package com.example.email_service.constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.mail.imap")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImapConfig {

    private String host;
    private String port;
    private String user;
    private String password;
    private String protocol;
    private String sslEnable;

}
