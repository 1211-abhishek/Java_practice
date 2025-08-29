package com.example.email_service_task.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class AttachmentService {

    @Async("taskExecutor")
    public void saveAttachments(InputStream attachmentInputStream, String fileName) {
        String newFileName = UUID.randomUUID().toString().substring(0, 4) + fileName;

        try (FileOutputStream fileOutputStream =new FileOutputStream("C:\\Users\\Sreenivas Bandaru\\Desktop\\java_prac\\Java_practice\\email_service_task\\attachments\\" + newFileName)){
            fileOutputStream.write(attachmentInputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
