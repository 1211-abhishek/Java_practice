package com.example.crud_on_excel.controller;

import com.example.crud_on_excel.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping("/")
    public String readExcel(@RequestParam MultipartFile multipartFile) throws IOException {

        return fileService.readExcelFile(multipartFile);
    }
}
