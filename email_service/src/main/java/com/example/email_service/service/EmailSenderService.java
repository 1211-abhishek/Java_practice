package com.example.email_service.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class EmailSenderService {

    int i = 0;

    @Autowired
    private JavaMailSender javaMailSender;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");

    // @Scheduled(fixedDelay = 10000)
    public void sendSimpleMailAtFixedRate() {

        System.out.println("sending mail : " + LocalDateTime.now().format(formatter));
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo("abhishekghogare.java@gmail.com");
        simpleMailMessage.setSubject("mail test : " + i);
//        simpleMailMessage.setText("This is test mail " + i++);
        simpleMailMessage.setText("This is test mail " + i++ + System.lineSeparator() + "Date time : " + LocalDateTime.now());
        System.out.println(simpleMailMessage);
        javaMailSender.send(simpleMailMessage);

        System.out.println("mail sent : " + LocalDateTime.now().format(formatter));

    }

    @Async
    public String sendMimeMail(String message) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setTo("abhishekghogare.java@gmail.com");
        mimeMessageHelper.setSubject("Test Mime message : " + i);

        mimeMessageHelper.setText(message, true);
        mimeMessageHelper.addAttachment("report.pdf", new File("src/main/resources/reports/report.pdf"));

        ClassPathResource classPathResource = new ClassPathResource("doc/aadhar8.jpeg");

        String htmlImage = "<img src='cid:image'/>";
        mimeMessageHelper.setText(htmlImage, true);
        mimeMessageHelper.addInline("image", classPathResource);

        javaMailSender.send(mimeMessage);

        return "Success";
    }
}
