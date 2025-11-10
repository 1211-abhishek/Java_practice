package com.example.kafka_producer.producers;

import com.example.kafka_producer.models.EmployeeModel;
import lombok.Data;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Data
@Service
public class EmployeeProducer {

    @Autowired
    public KafkaTemplate<String, EmployeeModel> kafkaTemplate;

    @Autowired
    KafkaProducer<String, EmployeeModel> kafkaProducer;

    public void sendObjMessage() {
        System.out.println("Sending object message");

        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setEmployeeId(101);
        employeeModel.setEmployeeName("John Doe");

        Map<MetricName, ? extends Metric> metrics = kafkaProducer.metrics();
        System.out.println("Metrics : " + metrics);

        kafkaTemplate.send("employee-topic", employeeModel);
        System.out.println("Object message sent: " + employeeModel);
    }
}
