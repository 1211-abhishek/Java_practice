package com.example.tesseract.controller;

import com.example.tesseract.entity.Aadhar;
import com.example.tesseract.service.OCRService;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
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

    @GetMapping("/export-pdf")
    public ResponseEntity<byte[]> exportToPdf() {
        ByteArrayOutputStream in = ocrService.exportAadharToPdf();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=students.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(in.toByteArray());
    }
}
