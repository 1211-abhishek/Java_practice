package com.example.email_service_task;

import com.example.email_service_task.config.IMAPConfig;
import com.example.email_service_task.config.MessageFolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableConfigurationProperties({IMAPConfig.class, MessageFolder.class})
public class EmailServiceTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailServiceTaskApplication.class, args);
	}

}
