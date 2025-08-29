package com.example.email_service_task.service;

import com.example.email_service_task.config.IMAPConfig;
import com.example.email_service_task.config.MessageFolder;
import com.example.email_service_task.exceptions.SomethingWentWrongException;
import com.example.email_service_task.model.EmailFetch;
import jakarta.mail.*;
import jakarta.mail.search.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReadEmailService {

    @Autowired
    IMAPConfig imapConfig;
    @Autowired
    MessageFolder messageFolder;

    @Autowired
    AttachmentService attachmentService;

    @Autowired
    ExtractContentService extractContentService;

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
//         emailResponseList =
//                messageList.parallelStream()
//                        .map(this::prepareEmailResponseList2)
//                        .collect(Collectors.toList());


//        HashSet<Object> hashSet = new HashSet<>();
//        ConcurrentHashMap<String,Object> concurrentHashMap = new ConcurrentHashMap<>();
//
        messageList.parallelStream().forEach(message -> {
            prepareEmailResponseList(message, emailResponseList);
        });
        return emailResponseList;

    }


    private EmailFetch prepareEmailResponseList2(Message message) {
        Object content;
        try {
            content = message.getContent();
        } catch (IOException | MessagingException e) {
            throw new SomethingWentWrongException("Something went wrong");
        }
        Map<String, List<String>> contentList;
        try {
            contentList = extractContentService.getContent(content);
        } catch (MessagingException | IOException e) {
            throw new SomethingWentWrongException("Something went wrong");
        }

        try {
            return new EmailFetch(Arrays.toString(message.getFrom()), message.getSentDate(), message.getReceivedDate(), message.getSubject(), contentList);
        } catch (MessagingException e) {
            throw new SomethingWentWrongException("Something went wrong");
        }
    }


    private void prepareEmailResponseList(Message message, List<EmailFetch> emailResponseList) {
        Object content;
        try {
            content = message.getContent();
        } catch (IOException | MessagingException e) {
            throw new SomethingWentWrongException("Something went wrong");
        }
        Map<String, List<String>> contentList;
        try {
            contentList = extractContentService.getContent(content);
        } catch (MessagingException | IOException e) {
            throw new SomethingWentWrongException("Something went wrong");
        }

        try {
            emailResponseList.add(new EmailFetch(Arrays.toString(message.getFrom()), message.getSentDate(), message.getReceivedDate(), message.getSubject(), contentList));
        } catch (MessagingException e) {
            throw new SomethingWentWrongException("Something went wrong");
        }
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

        List<Message> messageList;

        if (count == 0 || messageCount < count) {
            messageList = Arrays.asList(unseenMails);
        } else {
             int startIndex = Math.max(messageCount - count, 1);
            messageList = Arrays.asList(unseenMails).subList(startIndex, messageCount);
        }
        List<EmailFetch> emailResponseList = new ArrayList<>();

        messageList.forEach(message -> {

            prepareEmailResponseList(message, emailResponseList);

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
            prepareEmailResponseList(message, emailResponseList);
        });
        return emailResponseList;

    }

    public List<EmailFetch> getLastEmails(int count) throws MessagingException, IOException {

        Store store = getStore();
        store.connect(imapConfig.getHost(), imapConfig.getUser(), imapConfig.getPassword());
        Folder inbox = store.getFolder(messageFolder.getFolder().get(0));
        inbox.open(Folder.READ_ONLY);

        int messageCount = inbox.getMessageCount();
        Message[] messages;

        if (messageCount < count) {
            messages = inbox.getMessages(1, messageCount);
        } else {
            messages = inbox.getMessages(1, count);
        }

        List<Message> messageList = Arrays.asList(messages);
        List<EmailFetch> emailResponseList = new ArrayList<>();

        messageList.forEach(message -> {
            prepareEmailResponseList(message, emailResponseList);
        });
        return emailResponseList;
    }

    public List<EmailFetch> getEmailsFromSender(String email, int count) throws MessagingException, IOException {

        Store store = getStore();
        store.connect(imapConfig.getHost(), imapConfig.getUser(), imapConfig.getPassword());
        Folder inbox = store.getFolder(messageFolder.getFolder().get(0));
        inbox.open(Folder.READ_ONLY);

        FromStringTerm fromStringTerm = new FromStringTerm(email);

        Message[] mailsBySender = inbox.search(fromStringTerm);
        Arrays.sort(mailsBySender, Comparator.comparingInt(Message::getMessageNumber));

        int messageCount = mailsBySender.length;
        List<Message> messageList;

        if (count == 0 || messageCount < count) {
            messageList = Arrays.asList(mailsBySender);
        } else {
            int startIndex = Math.max(messageCount - count, 1);
            messageList = Arrays.asList(mailsBySender).subList(startIndex, messageCount);
        }
        List<EmailFetch> emailResponseList = new ArrayList<>();

        messageList.forEach(message -> {
            prepareEmailResponseList(message, emailResponseList);
        });
        return emailResponseList;
    }

    public List<EmailFetch> getEmailsByFilter(String receivedFrom, String subject, Date startDate, Date endDate, int count) throws MessagingException {

        Store store = getStore();
        store.connect(imapConfig.getHost(), imapConfig.getUser(), imapConfig.getPassword());
        Folder inbox = store.getFolder(messageFolder.getFolder().get(0));
        inbox.open(Folder.READ_ONLY);

//        FromStringTerm fromStringTerm = new FromStringTerm(receivedFrom);
//        SubjectTerm subjectTerm = new SubjectTerm(subject);
//        DateTerm startDateTerm = new SentDateTerm(ComparisonTerm.GE,startDate);
//        DateTerm  endDateTerm = new SentDateTerm(ComparisonTerm.LE,endDate);
//        AndTerm andTerm = new AndTerm(new SearchTerm[]{
//                fromStringTerm,subjectTerm,startDateTerm,endDateTerm
//        });

        List<SearchTerm> terms = new ArrayList<>();

        if (receivedFrom != null && !receivedFrom.isBlank()) {
            terms.add(new FromStringTerm(receivedFrom));
        }
        if (subject != null && !subject.isBlank()) {
            terms.add(new SubjectTerm(subject));
        }
        if (startDate != null) {
            terms.add(new SentDateTerm(ComparisonTerm.GE, startDate));
        }
        if (endDate != null) {
            terms.add(new SentDateTerm(ComparisonTerm.LE, endDate));
        }
        SearchTerm[] searchTerms;
        searchTerms = terms.toArray(new SearchTerm[0]);

        AndTerm andTerm = new AndTerm(searchTerms);

        Message[] filteredMails = inbox.search(andTerm);
        Arrays.sort(filteredMails, Comparator.comparingInt(Message::getMessageNumber));

        int messageCount = filteredMails.length;
        List<Message> messageList;
        if (count == 0) {
            messageList = Arrays.asList(filteredMails);
        } else {
            int startIndex = Math.max(messageCount - count, 1);
            messageList = Arrays.asList(filteredMails).subList(startIndex, messageCount);
        }
        List<EmailFetch> emailResponseList = new ArrayList<>();

        messageList.forEach(message -> {
            prepareEmailResponseList(message, emailResponseList);
        });
        return emailResponseList;
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
