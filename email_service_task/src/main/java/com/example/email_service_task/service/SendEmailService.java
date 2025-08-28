package com.example.email_service_task.service;

import com.example.email_service_task.exceptions.FailedToSendEmailException;
import com.example.email_service_task.model.EmailModel;
import com.example.email_service_task.responces.MailSentSuccessResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class SendEmailService {

    @Autowired
    JavaMailSender javaMailSender;


    public MailSentSuccessResponse sendSimpleMail(EmailModel emailModel) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(emailModel.getTo());
        simpleMailMessage.setCc(emailModel.getCc());
        simpleMailMessage.setBcc(emailModel.getBcc());
        simpleMailMessage.setText(emailModel.getText());
        simpleMailMessage.setSubject(emailModel.getSubject());

        try {
            javaMailSender.send(simpleMailMessage);
        } catch (MailException e) {
            throw new FailedToSendEmailException("Failed to send email to : " + Arrays.toString(simpleMailMessage.getTo()));
        }

        List<String> fileNameList = new ArrayList<>();
        fileNameList.add("No files Attached");
        MailSentSuccessResponse emailSentSuccessfully = new MailSentSuccessResponse("Email sent successfully", HttpStatus.OK.value(), HttpStatus.OK, emailModel, fileNameList);
        return emailSentSuccessfully;
    }

    public MailSentSuccessResponse sendHtmlMail(EmailModel emailModel) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        List<String> fileNames = new ArrayList<>();

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED);
            mimeMessageHelper.setTo(emailModel.getTo());
            mimeMessageHelper.setCc(emailModel.getCc());
            if (emailModel.getBcc() != null) {

                mimeMessageHelper.setBcc(emailModel.getBcc());
            }
            mimeMessageHelper.setSubject(emailModel.getSubject());
            mimeMessageHelper.setText(emailModel.getText(), true);
            List<MultipartFile> attachments = emailModel.getAttachments();
            String text = emailModel.getText();

            for (MultipartFile multipartFile : attachments) {

                String uuid = UUID.randomUUID().toString().substring(0, 4);
                if (text.contains("{imageId}")) {

//                    if (attachments.size() < count){
//                        throw new InlineImageNotProvidedException("Inline image not provided");
//                    }
//                    count++;
                    text = text.replaceFirst("\\{imageId}", uuid);
                    mimeMessageHelper.addInline(uuid, multipartFile, multipartFile.getContentType());
                } else {
                    String originalFilename = multipartFile.getOriginalFilename();
                    mimeMessageHelper.addAttachment(originalFilename, multipartFile);
                    fileNames.add(originalFilename);
                }
            }
            mimeMessageHelper.setText(text, true);
            emailModel.setText(text);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        javaMailSender.send(mimeMessage);
        return new MailSentSuccessResponse("Email sent successfully", HttpStatus.OK.value(), HttpStatus.OK, emailModel, fileNames);
    }


}
