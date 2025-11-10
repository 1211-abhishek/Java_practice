package com.example.kafka_consumer.consumer;

import com.example.kafka_consumer.models.EmployeeModel;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class ConsumeObjMessages {

    @KafkaListener(topics = "employee-topic")
    public void readObjMessage(EmployeeModel employeeModel) {
        System.out.println("reading object message");
        System.out.println("Object message got : " + employeeModel);
    }
}
