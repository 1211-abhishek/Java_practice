package com.example.email_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailModel {

    private String[] to;
    private String from;
    private String subject;
    private String[] cc;
    private String text;
    private MultipartFile multipartFile;

}
