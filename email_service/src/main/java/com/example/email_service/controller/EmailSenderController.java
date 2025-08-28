package com.example.email_service.controller;

import com.example.email_service.responces.EmailResponse;
import com.example.email_service.service.EmailSenderService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

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

    @PostMapping("/send-simple-html")
    public String sendSimpleMail(@RequestBody String message){

        return emailSenderService.sendSimpleMail(message);
    }

    @GetMapping("/get-mails")
    public void getEmail(){
        try {
            emailSenderService.getAllMails();
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get-mails-by-date")
    public void getEmailByDate(@RequestParam Date date){
        try {
            emailSenderService.getMessageFromDate(date);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get-mails-by-email")
    public List<EmailResponse> getEmailByEmail(@RequestParam String email){
        try {
            return emailSenderService.getMessageByReceivedFrom(email);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}
