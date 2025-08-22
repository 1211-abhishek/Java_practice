package com.example.translate_service.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TranslationService {

    public String translateText(String originalText) {

        log.info("Original Text : {}", originalText);

        TranslatorText translatorText = new TranslatorText();

        String responseText = translatorText.post(originalText);

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonData;
        try {
            jsonData = objectMapper.readTree(responseText);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        JsonNode jsonNode = jsonData.get(0).get("translations").get(0);
        String translatedText = jsonNode.get("text").asText();
        log.info("Translated Text : {}", translatedText);

        return translatedText;

    }
}
