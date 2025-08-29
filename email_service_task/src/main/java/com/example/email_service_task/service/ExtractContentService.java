package com.example.email_service_task.service;

import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class ExtractContentService {

    @Autowired
    AttachmentService attachmentService;

    @Async("taskExecutor")
    public void getMessageContent(List<Message> messageList, ConcurrentHashMap<String,Object> concurrentHashMap) {

       messageList.forEach(message -> {
           try {
               Object content = message.getContent();
               String key = UUID.randomUUID().toString().substring(0,4);
               concurrentHashMap.put(key,content);
           } catch (IOException | MessagingException e) {
               throw new RuntimeException(e);
           }
       });
    }
    //@Async("taskExecutor")
    public Map<String, List<String>> getContent(Object content) throws MessagingException, IOException {
        System.out.println("In get content method - Thread : " + Thread.currentThread().getName() + " : " + Thread.currentThread().getId());

        Map<String, List<String>> contentList = new HashMap<>();
        List<String> textList = new ArrayList<>();
        List<String> htmlList = new ArrayList<>();
        List<String> fileNameList = new ArrayList<>();


        if (content instanceof String) {
            textList.add((String) content);
            contentList.put("text", textList);
        } else if (content instanceof Multipart) {
            Multipart multipart = (Multipart) content;
            for (int i = 0; i < multipart.getCount(); i++) {

                BodyPart bodyPart = multipart.getBodyPart(i);

                if (bodyPart.isMimeType("text/plain")) {
                    textList.add((String) bodyPart.getContent());
                    contentList.put("text", textList);
                } else if (bodyPart.isMimeType("text/html")) {
                    htmlList.add((String) bodyPart.getContent());
                    contentList.put("html", htmlList);
                } else {
                    String fileName = bodyPart.getFileName();
                    if (fileName != null && !fileName.isBlank()) {
                        fileNameList.add(fileName);
                        attachmentService.saveAttachments(bodyPart.getInputStream(), fileName);
                    }
                }
            }
            if (!fileNameList.isEmpty()) {
                contentList.put("files", fileNameList);

            }
        }

        return contentList;
    }

}
