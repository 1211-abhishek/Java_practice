package com.example.tesseract.controller;

import com.example.tesseract.entity.Aadhar;
import com.example.tesseract.service.OCRService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/ocr")
public class OCRController {

    @Autowired
    OCRService ocrService;

    @PostMapping("/")
    public Aadhar postAadharData(@RequestParam MultipartFile multipartFile) throws TesseractException, IOException {

       return ocrService.postAadharData(multipartFile);
    }


}
