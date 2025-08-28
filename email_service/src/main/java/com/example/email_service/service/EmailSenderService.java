package com.example.email_service.service;

import com.example.email_service.constants.ImapConfig;
import com.example.email_service.constants.MessageFolder;
import com.example.email_service.responces.EmailResponse;
import jakarta.mail.*;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.search.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class EmailSenderService {

    int i = 0;

    @Autowired
    private MessageFolder messageFolder;

    @Autowired
    private ImapConfig imapConfig;

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

    public String sendSimpleMail(String text) {
        System.out.println("Sending simple mail");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("abhishekghogare.java@gmail.com");
        simpleMailMessage.setSubject("sub something");
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
        System.out.println("Sent simple mail");
        return "Success";
    }

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

    public void getAllMails() throws MessagingException, IOException {

        Properties properties = new Properties();

        properties.put("mail.imap.host", imapConfig.getHost());
        properties.put("mail.imap.port", imapConfig.getPort());
        properties.put("mail.imap.protocol", imapConfig.getProtocol());
        properties.put("mail.imap.ssl.enable", "true");


//        Session session = Session.getInstance(properties, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(imapConfig.getUser(), imapConfig.getPassword());
//            }
//        });

        Session session = Session.getInstance(properties);
        Store store = session.getStore(imapConfig.getProtocol());
        store.connect(imapConfig.getHost(), imapConfig.getUser(), imapConfig.getPassword());

        Folder[] list = store.getDefaultFolder().list("*");
        Arrays.stream(list).forEach(System.out::println);

        Folder inbox = store.getFolder(messageFolder.getFolder().get(2));
        inbox.open(Folder.READ_ONLY);
        int messageCount = inbox.getMessageCount();
        int startIndex = Math.max(messageCount - 5, 1);


        Message[] messages = inbox.getMessages(startIndex, messageCount);
        Arrays.sort(messages, Comparator.comparing(Message::getMessageNumber));

//        Arrays.stream(messages).forEach(message -> System.out.println(message.getMessageNumber()));

        for (Message message : messages) {

            Address[] from = message.getFrom();
            Address[] allRecipients = message.getAllRecipients();
            String subject = message.getSubject();
            Flags flags = message.getFlags();
            Object content = message.getContent();
            String contentType = message.getContentType();
            String description = message.getDescription();
            String description1 = message.getDisposition();


            System.out.println("From: " + Arrays.toString(from));
            System.out.println("All Recipients: " + Arrays.toString(allRecipients));
            System.out.println("Subject: " + subject);
            System.out.println("Flags: " + flags);
            System.out.println("Content: " + content);
            System.out.println("Content Type: " + contentType);
            System.out.println("Description: " + description);
            System.out.println("Disposition: " + description1);

            if (content instanceof String) {
                System.out.println("Body: " + content);
            } else if (content instanceof Multipart) {
                Multipart multipart = (Multipart) content;
                for (int i = 0; i < multipart.getCount(); i++) {
                    BodyPart bodyPart = multipart.getBodyPart(i);
                    String partType = bodyPart.getContentType();

                    System.out.println("---- Part " + (i + 1) + " (" + partType + ") ----");

                    if (bodyPart.isMimeType("text/plain")) {
                        System.out.println("Text: " + bodyPart.getContent());
                    } else if (bodyPart.isMimeType("text/html")) {
                        System.out.println("HTML: " + bodyPart.getContent());
                    } else {
                        System.out.println("Attachment/Other Content: " + bodyPart.getFileName());
                    }
                }
            }
            System.out.println("------------");
        }

        System.out.println(messages.length);
        System.out.println(inbox.getMessageCount());
        inbox.close(false);
        store.close();
    }

    public void getMessageFromDate(Date date) throws MessagingException {

        Store store = getStore();

        Folder inbox = store.getFolder(messageFolder.getFolder().get(0));
        inbox.open(Folder.READ_ONLY);

//        FlagTerm flagTerm = new FlagTerm(new Flags(Flags.Flag.SEEN) , false);
        ReceivedDateTerm receivedDateTerm = new ReceivedDateTerm(ReceivedDateTerm.EQ, date);

//        AndTerm andTerm = new AndTerm(new SearchTerm[]{flagTerm,receivedDateTerm});
        Message[] messages = inbox.search(receivedDateTerm);

        Arrays.stream(messages).forEach(message -> {
            System.out.println(message.getMessageNumber());
        });


//        Message[] messages = inbox.getMessages();
//        long messageCount = Arrays.stream(messages).filter(message -> {
//            try {
//                Date received = message.getReceivedDate();
//                LocalDate receivedDate = received.toInstant()
//                        .atZone(ZoneId.systemDefault())
//                        .toLocalDate();
//
//                LocalDate today = LocalDate.now();
//
//                return receivedDate.equals(today);
//            } catch (MessagingException e) {
//                System.out.println("no messages found");
//                throw new RuntimeException(e);
//            }
//        }).count();
//        System.out.println(messageCount);

    }

    public List<EmailResponse> getMessageByReceivedFrom(String email) throws MessagingException {

        Store store = getStore();
        Folder inbox = store.getFolder(messageFolder.getFolder().get(0));
        inbox.open(Folder.READ_ONLY);
        FromStringTerm fromStringTerm = new FromStringTerm(email);
        Message[] messages = inbox.search(fromStringTerm);
        System.out.println(messages.length);

        List<Message> messageList = Arrays.asList(messages);
        List<EmailResponse> emailResponseList = new ArrayList<>();

        messageList.stream().forEach(message -> {

            Object content = null;
            try {
                content = message.getContent();
            } catch (IOException | MessagingException e) {
                throw new RuntimeException(e);
            }
            List<List<String >> contentList;
            try {
                contentList = getContent(content);
            } catch (MessagingException | IOException e) {
                throw new RuntimeException(e);
            }

            try {
                System.out.println(message.getMessageNumber());
                emailResponseList.add(new EmailResponse(Arrays.toString(message.getFrom()), message.getSentDate(), message.getReceivedDate(), message.getSubject(), contentList));
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

        });

        return emailResponseList;


    }

    public List<List<String>> getContent(Object content) throws MessagingException, IOException {

        List<List<String>> contentList = new ArrayList<>();
        List<String> textList = new ArrayList<>();
        List<String> htmlList = new ArrayList<>();
        List<String> fileNameList = new ArrayList<>();

        if (content instanceof String) {
            textList.add((String) content);
            contentList.add(textList);
        } else if (content instanceof Multipart) {
            Multipart multipart = (Multipart) content;
            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                String partType = bodyPart.getContentType();

                if (bodyPart.isMimeType("text/plain")) {
                    textList.add((String) bodyPart.getContent());
                    contentList.add(textList);
                } else if (bodyPart.isMimeType("text/html")) {
                    htmlList.add((String) bodyPart.getContent());
                    contentList.add(htmlList);
                } else {
                    String fileName = bodyPart.getFileName();
                    if (fileName != null && !fileName.isBlank()) {
                        fileNameList.add(fileName);
                    }
                }
            }
            if (!fileNameList.isEmpty()) {
                contentList.add(fileNameList);
            }
        }

        return contentList;
    }

    public Store getStore() {
        Properties properties = new Properties();
        properties.put("mail.imap.host", imapConfig.getHost());
        properties.put("mail.imap.port", imapConfig.getPort());
        properties.put("mail.imap.user", imapConfig.getUser());
        properties.put("mail.imap.ssl.enable", true);

        Session instance = Session.getInstance(properties);
        Store store = null;
        try {
            store = instance.getStore(imapConfig.getProtocol());
            store.connect(imapConfig.getHost(), imapConfig.getUser(), imapConfig.getPassword());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return store;

    }
}
