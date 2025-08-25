package com.example.schedular_app.service;

import com.example.schedular_app.entity.LogEntity;
import com.example.schedular_app.entity.LogType;
import com.example.schedular_app.entity.SchedularType;
import com.example.schedular_app.repository.LogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DBService {

    @Autowired
    LogRepository logRepository;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");


    int i = 1;

    @Scheduled(fixedDelay = 2000)
    public void fixedDelaySchedular() throws InterruptedException {
        System.out.println("fixeddelay started at : " + LocalDateTime.now().format(formatter));


        LogEntity logEntity = new LogEntity();
        logEntity.setLogId((long) i++);
        logEntity.setLogType(LogType.INFO);
        logEntity.setSchedularType(SchedularType.FIXED_DELAY.toString());
        logEntity.setLogTimeStamp(LocalDateTime.now());
        logEntity.setDescription("Fixed delay schedular");
        LogEntity savedLog = logRepository.save(logEntity);
        System.out.println(savedLog);

        Thread.sleep(5000);

        System.out.println("fixeddelay completed at : " + LocalDateTime.now().format(formatter));

    }

    @Scheduled(cron = "0 0/5 * * * *")
    public void cronSchedular(){

        LogEntity logEntity = new LogEntity();

        logEntity.setLogId((long) i++);
        logEntity.setLogType(LogType.INFO);
        logEntity.setSchedularType(SchedularType.CRON_JOB.toString());
        logEntity.setLogTimeStamp(LocalDateTime.now());
        logEntity.setDescription("Cron schedular");

        System.out.println("Cron called");

        logRepository.save(logEntity);
    }
}
