package com.example.translate_service.service;

import com.example.translate_service.AzureConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
public class TranslatorText {

    private static final String key = AzureConfig.azureKey;
    private static final String location = AzureConfig.location;

    private final WebClient webClient;

    public TranslatorText() {
        this.webClient = WebClient.builder()
                .baseUrl(AzureConfig.baseURL)
                .defaultHeader("Ocp-Apim-Subscription-Key", key)
                .defaultHeader("Ocp-Apim-Subscription-Region", location)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    @Scheduled()
    public String post(String originalText) {

        String body = "[{\"Text\": \" " + originalText + "\"}]";


        return webClient.post()
                .uri("/translate?api-version=3.0&from=ar&to=en")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // block for sync call, remove if you want async
    }


}
