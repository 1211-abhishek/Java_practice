package com.example.translate_service.controller;

import com.example.translate_service.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/translate")
public class TranslationController {

    @Autowired
    TranslationService translationService;

    @PostMapping("/")
    public String postCheck(@RequestParam String originalText){

        return translationService.translateText(originalText);
    }
}
