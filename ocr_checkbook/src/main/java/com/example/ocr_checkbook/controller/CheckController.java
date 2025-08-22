package com.example.ocr_checkbook.controller;

import com.example.ocr_checkbook.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/check")
public class CheckController {

    @Autowired
    CheckService checkService;
    @PostMapping("/")
    public String postCheck(@RequestParam MultipartFile multipartFile){

        try {
            return checkService.readCheck(multipartFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
