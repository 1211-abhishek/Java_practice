package com.example.spring_boot_kafka_example;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    private final MessageProducer producer;

    public KafkaController(MessageProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/publish")
    public String publish(@RequestParam String message) {
        producer.sendMessage(message);
        return "Message sent to Kafka topic!";
    }
}
