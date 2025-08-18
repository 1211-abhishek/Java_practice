package com.example.tabula.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.tabula.service.TabulaService;

@RestController
@RequestMapping("/tabula")
public class TabulaController {

    @Autowired
    TabulaService tabulaService;

    @PostMapping("/pdf")
    public void extractTable(@RequestParam MultipartFile multipartFile){

        tabulaService.extractTableUsingTabula(multipartFile);
    }
}
