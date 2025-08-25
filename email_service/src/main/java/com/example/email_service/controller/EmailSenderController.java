package com.example.email_service.controller;

import com.example.email_service.service.EmailSenderService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailSenderController {

    @Autowired
    EmailSenderService emailSenderService;

    @PostMapping("/send-html")
    public String sendMail(@RequestBody String message){

        try {
            return emailSenderService.sendMimeMail(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
