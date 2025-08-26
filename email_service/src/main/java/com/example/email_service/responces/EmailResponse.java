package com.example.email_service.responces;

import jakarta.mail.Multipart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailResponse {

    private String from;
//    private String to;
    private Date sentDate;
    private Date receivedDate;
    private String subject;
    private List multipartFile;

}
