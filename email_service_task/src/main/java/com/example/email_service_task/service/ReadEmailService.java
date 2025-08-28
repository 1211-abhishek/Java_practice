package com.example.email_service_task.service;

import com.example.email_service_task.config.IMAPConfig;
import com.example.email_service_task.config.MessageFolder;
import com.example.email_service_task.exceptions.FailedToSendEmailException;
import com.example.email_service_task.model.EmailFetch;
import com.example.email_service_task.model.EmailModel;
import com.example.email_service_task.responces.MailSentSuccessResponse;
import jakarta.mail.*;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.search.FlagTerm;
import jakarta.mail.search.FromStringTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class ReadEmailService {

    @Autowired
    IMAPConfig imapConfig;
    @Autowired
    MessageFolder messageFolder;

    public List<EmailFetch> getRecentMails(int count) throws MessagingException, IOException {

        Store store = getStore();
        store.connect(imapConfig.getHost(), imapConfig.getUser(), imapConfig.getPassword());
        Folder inbox = store.getFolder(messageFolder.getFolder().get(0));
        inbox.open(Folder.READ_ONLY);
        int messageCount = inbox.getMessageCount();
        int startIndex = Math.max(messageCount - count + 1, 1);

        Message[] messages = inbox.getMessages(startIndex, messageCount);

        List<Message> messageList = Arrays.asList(messages);
        List<EmailFetch> emailResponseList = new ArrayList<>();

        messageList.forEach(message -> {

            Object content = null;
            try {
                content = message.getContent();
            } catch (IOException | MessagingException e) {
                throw new RuntimeException(e);
            }
            Map<String, List<String>> contentList;
            try {
                contentList = getContent(content);
            } catch (MessagingException | IOException e) {
                throw new RuntimeException(e);
            }

            try {
                System.out.println(message.getMessageNumber());
                emailResponseList.add(new EmailFetch(Arrays.toString(message.getFrom()), message.getSentDate(), message.getReceivedDate(), message.getSubject(), contentList));
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
        return emailResponseList;

    }

    public List<EmailFetch> getRecentUnseenEmails(int count) throws MessagingException, IOException {

        Store store = getStore();
        store.connect(imapConfig.getHost(), imapConfig.getUser(), imapConfig.getPassword());
        Folder inbox = store.getFolder(messageFolder.getFolder().get(0));
        inbox.open(Folder.READ_ONLY);

        FlagTerm seen = new FlagTerm(new Flags(Flags.Flag.SEEN), false);

        Message[] unseenMails = inbox.search(seen);
        Arrays.sort(unseenMails, Comparator.comparingInt(Message::getMessageNumber));

        int messageCount = unseenMails.length;
        int startIndex = Math.max(messageCount - count, 1);

        List<Message> messageList = Arrays.asList(unseenMails).subList(startIndex, messageCount);
        List<EmailFetch> emailResponseList = new ArrayList<>();

        messageList.forEach(message -> {

            Object content = null;
            try {
                content = message.getContent();
            } catch (IOException | MessagingException e) {
                throw new RuntimeException(e);
            }
            Map<String, List<String>> contentList;
            try {
                contentList = getContent(content);
            } catch (MessagingException | IOException e) {
                throw new RuntimeException(e);
            }

            try {
                System.out.println(message.getMessageNumber());
                emailResponseList.add(new EmailFetch(Arrays.toString(message.getFrom()), message.getSentDate(), message.getReceivedDate(), message.getSubject(), contentList));
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
        return emailResponseList;

    }

    public List<EmailFetch> getLastUnseenEmails(int count) throws MessagingException, IOException {

        Store store = getStore();
        store.connect(imapConfig.getHost(), imapConfig.getUser(), imapConfig.getPassword());
        Folder inbox = store.getFolder(messageFolder.getFolder().get(0));
        inbox.open(Folder.READ_ONLY);

        FlagTerm seen = new FlagTerm(new Flags(Flags.Flag.SEEN), false);

//        Message[] unseenMails = inbox.search(seen);
//        Arrays.sort(unseenMails,Comparator.comparingInt(Message::getMessageNumber));
//
//        int start = Math.max(unseenMails.length - count, 0);
//        Message[] messages = Arrays.copyOfRange(unseenMails, start, unseenMails.length);
//
        Message[] unseenMails = inbox.search(seen);
        Arrays.sort(unseenMails, Comparator.comparingInt(Message::getMessageNumber));

        List<Message> messageList = Arrays.asList(unseenMails);
        List<EmailFetch> emailResponseList = new ArrayList<>();

        messageList.stream().limit(count).forEach(message -> {

            Object content = null;
            try {
                content = message.getContent();
            } catch (IOException | MessagingException e) {
                throw new RuntimeException(e);
            }
            Map<String, List<String>> contentList;
            try {
                contentList = getContent(content);
            } catch (MessagingException | IOException e) {
                throw new RuntimeException(e);
            }

            try {
                System.out.println(message.getMessageNumber());
                emailResponseList.add(new EmailFetch(Arrays.toString(message.getFrom()), message.getSentDate(), message.getReceivedDate(), message.getSubject(), contentList));
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
        return emailResponseList;

    }

    public List<EmailFetch> getLastEmails(int count) throws MessagingException, IOException {

        Store store = getStore();
        store.connect(imapConfig.getHost(), imapConfig.getUser(), imapConfig.getPassword());
        Folder inbox = store.getFolder(messageFolder.getFolder().get(0));
        inbox.open(Folder.READ_ONLY);

        Message[] messages = inbox.getMessages(1, count);

        List<Message> messageList = Arrays.asList(messages);
        List<EmailFetch> emailResponseList = new ArrayList<>();

        messageList.forEach(message -> {

            Object content = null;
            try {
                content = message.getContent();
            } catch (IOException | MessagingException e) {
                throw new RuntimeException(e);
            }
            Map<String, List<String>> contentList;
            try {
                contentList = getContent(content);
            } catch (MessagingException | IOException e) {
                throw new RuntimeException(e);
            }

            try {
                System.out.println(message.getMessageNumber());
                emailResponseList.add(new EmailFetch(Arrays.toString(message.getFrom()), message.getSentDate(), message.getReceivedDate(), message.getSubject(), contentList));
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
        return emailResponseList;

    }


    public Map<String, List<String>> getContent(Object content) throws MessagingException, IOException {

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
                        //saveAttachments(bodyPart.getInputStream(),fileName);
                    }
                }
            }
            if (!fileNameList.isEmpty()) {
                contentList.put("files", fileNameList);

            }
        }

        return contentList;
    }

    @Async
    private void saveAttachments(InputStream attachmentInputStream, String fileName) {

        String newFileName = UUID.randomUUID().toString().substring(0, 4) + fileName;

        try (FileOutputStream fileOutputStream =new FileOutputStream("C:\\Users\\Sreenivas Bandaru\\Desktop\\java_prac\\Java_practice\\email_service_task\\attachments\\" + newFileName)){
            fileOutputStream.write(attachmentInputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private Store getStore() throws NoSuchProviderException {
        Properties properties = new Properties();
        properties.put("mail.imap.host", imapConfig.getHost());
        properties.put("mail.imap.user", imapConfig.getUser());
        properties.put("mail.imap.password", imapConfig.getPassword());
        properties.put("mail.imap.ssl", imapConfig.getSslEnable());

        Session instance = Session.getInstance(properties);
        return instance.getStore(imapConfig.getProtocol());
    }


}
