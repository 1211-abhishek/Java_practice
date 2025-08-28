package com.example.email_service_task.controller;

import com.example.email_service_task.model.EmailFetch;
import com.example.email_service_task.model.EmailModel;
import com.example.email_service_task.responces.MailSentSuccessResponse;
import com.example.email_service_task.service.ReadEmailService;
import com.example.email_service_task.service.SendEmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/emailservice")
public class EmailController {

    @Autowired
    SendEmailService sendEmailService;

    @Autowired
    ReadEmailService readEmailService;

    @PostMapping("/send-simple-mail")
    public ResponseEntity<MailSentSuccessResponse> sendSimpleMail(@RequestParam String subject,
                                                                  @RequestParam String[] to,
                                                                  @RequestParam(required = false) String[] cc,
                                                                  @RequestParam(required = false) String[] bcc,
                                                                  @RequestParam String text) {

        EmailModel emailModel = new EmailModel();
        emailModel.setSubject(subject);
        emailModel.setTo(to);
        emailModel.setCc(cc);
        emailModel.setBcc(bcc);
        emailModel.setText(text);
        MailSentSuccessResponse mailSentSuccessResponse = sendEmailService.sendSimpleMail(emailModel);
        return new ResponseEntity<>(mailSentSuccessResponse, HttpStatus.OK);
    }

    @PostMapping("/send-html-mail")
    public ResponseEntity<MailSentSuccessResponse> sendHtmlMail(@RequestParam String subject,
                                                                @RequestParam String[] to,
                                                                @RequestParam(required = false) String[] cc,
                                                                @RequestParam(required = false) String[] bcc,
                                                                @RequestParam String text,
                                                                @RequestParam List<MultipartFile> attachments
    ) {

        EmailModel emailModel = new EmailModel();
        emailModel.setSubject(subject);
        emailModel.setTo(to);
        emailModel.setCc(cc);
        emailModel.setBcc(bcc);
        emailModel.setText(text);
        emailModel.setAttachments(attachments);
        MailSentSuccessResponse mailSentSuccessResponse = sendEmailService.sendHtmlMail(emailModel);

        return new ResponseEntity<>(mailSentSuccessResponse, HttpStatus.OK);
    }

    @GetMapping("/get-recent-mails")
    public ResponseEntity<List<EmailFetch>> getRecentEmails(@RequestParam int count) {

        List<EmailFetch> recentMails;
        try {
            recentMails = readEmailService.getRecentMails(count);
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(recentMails, HttpStatus.OK);
    }

    @GetMapping("/get-last-mails")
    public ResponseEntity<List<EmailFetch>> getLastEmails(@RequestParam int count) {

        List<EmailFetch> lastMails;
        try {
            lastMails = readEmailService.getLastEmails(count);
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(lastMails, HttpStatus.OK);
    }

    @GetMapping("/get-last-unseen-mails")
    public ResponseEntity<List<EmailFetch>> getLastUnseenEmails(@RequestParam int count) {

        List<EmailFetch> lastUnseenEmails;
        try {
            lastUnseenEmails = readEmailService.getLastUnseenEmails(count);
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(lastUnseenEmails, HttpStatus.OK);
    }


    @GetMapping("/get-recent-unseen-mails")
    public ResponseEntity<List<EmailFetch>> getRecentUnseenEmails(@RequestParam int count) {

        List<EmailFetch> recentUnseenEmails;
        try {
            recentUnseenEmails = readEmailService.getRecentUnseenEmails(count);
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(recentUnseenEmails, HttpStatus.OK);
    }
}
