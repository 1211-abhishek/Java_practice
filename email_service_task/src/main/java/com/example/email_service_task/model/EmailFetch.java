package com.example.email_service_task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailFetch {

    String getFromEmail;
    Date sentDate;
    Date receivedDate;
    String subject;
    Map<String,List<String>> contentList;
}
