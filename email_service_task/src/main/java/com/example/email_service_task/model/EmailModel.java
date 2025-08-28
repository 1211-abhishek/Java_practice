package com.example.email_service_task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.mail.Multipart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailModel {

    private String subject;
    private String[] to;
    private String[] cc;
    private String[] bcc;
    private String text;
    @JsonIgnore
    private List<MultipartFile> attachments;
}
