package com.example.ocr_checkbook.service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CheckService {

    public String readCheck(MultipartFile multipartFile) throws Exception {

        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
        tesseract.setLanguage("eng");

        BufferedImage bufferedImage;
        try {
             bufferedImage = ImageIO.read(multipartFile.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        Path imagePath = Paths.get("images\\ocr-image-" + UUID.randomUUID().toString().substring(0, 3) + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf('.')));
//        Path imagePathPreProcessed = Paths.get(imagePath.toString().substring(0, multipartFile.getOriginalFilename().lastIndexOf('.')) + "procesed" + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf('.')));
//        //Path imagePathPreProcessed = Paths.get("ocr-image-preprocessed" + Objects.requireNonNull(multipartFile.getOriginalFilename()).substring(Objects.requireNonNull(multipartFile.getOriginalFilename()).lastIndexOf('.')));
//        Files.copy(multipartFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
//
//        OcrPreprocessor.preprocess(imagePath.toString(), imagePathPreProcessed.toString());
//        BufferedImage bufferedImage = ImageIO.read(imagePathPreProcessed.toFile());


        String data;
        try {
            data = tesseract.doOCR(bufferedImage);
        } catch (TesseractException e) {
            throw new RuntimeException(e);
        }

        return data;
        //String pythonOcr = externalOCR(multipartFile);

//        ChequeInfo chequeInfo = extractChequeInfo(data);
//        return chequeInfo.toString();
//        return pythonOcr;
    }

    public static String externalOCR(MultipartFile multipartFile) throws Exception {
        String url = "http://localhost:8000/ocr";

        RestTemplate restTemplate = new RestTemplate();

        ByteArrayResource resource = new ByteArrayResource(multipartFile.getBytes()) {
            @Override
            public String getFilename() {
                return multipartFile.getOriginalFilename(); // keep original filename
            }
        };

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", resource);

        HttpEntity<MultiValueMap<String, Object>> requestEntity =
                new HttpEntity<>(body, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(url, requestEntity, String.class);

        System.out.println("OCR Response: " + response.getBody());
        return response.getBody();
    }

    public static ChequeInfo extractChequeInfo(String ocrText) {

        Pattern payeePattern = Pattern.compile("Pay\\s+([A-Za-z ]+)", Pattern.CASE_INSENSITIVE);
        Pattern amountWordsPattern = Pattern.compile("Rupees\\s+([A-Za-z ]+?)\\s+Rupees", Pattern.CASE_INSENSITIVE);
        Pattern accountPattern = Pattern.compile("(?i)(?:a[/\\s]*c|acc(?:ount)?|arc)\\s*No\\.?[:}\\s-]*([0-9\\s\\-]{10,30})");

        String payeeName = "Not Found";
        Matcher payeeMatcher = payeePattern.matcher(ocrText);
        if (payeeMatcher.find()) {
            payeeName = payeeMatcher.group(1).trim();
        }

        String amountInWords = "Not Found";
        Matcher amountWordsMatcher = amountWordsPattern.matcher(ocrText);
        if (amountWordsMatcher.find()) {
            amountInWords = amountWordsMatcher.group(1).trim();
        }

        String accountNumber = "Not Found";
        Matcher accountMatcher = accountPattern.matcher(ocrText);
        if (accountMatcher.find()) {
            accountNumber = accountMatcher.group(1).replaceAll("[^0-9]", ""); // keep only digits
        }

        return new ChequeInfo(payeeName, amountInWords, accountNumber);
    }

    }
