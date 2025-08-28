package com.example.email_service_task.responces;

import com.example.email_service_task.model.EmailModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailSentSuccessResponse {

    private String message;
    private int statusCode;
    private HttpStatus httpStatus;
    private EmailModel emailModel;
    private List<String> attachmentFileNames;
}
