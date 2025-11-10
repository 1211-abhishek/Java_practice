package com.example.rabbitmq.tut1;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Tut1Sender {

    @Autowired
    public RabbitTemplate rabbitTemplate;

    public void sendOrder() {

        rabbitTemplate.convertAndSend(Constants.DIRECT_EXCHANGE, Constants.ROUTING_KEY2, "order 1");
    }

    int i = 0;
    @Scheduled(fixedRate = 10000)
    public void topicExchangeDemo1() {
        rabbitTemplate.convertAndSend(Constants.TOPIC_EXCHANGE, "payment.pending", "pending payment : " + i++);
    }
//
//    @Scheduled(fixedRate = 5000)
//    public void topicExchangeDemo2() {
//        rabbitTemplate.convertAndSend(Constants.TOPIC_EXCHANGE, "payment.on.hold", "on hold payment");
//    }
//
//    @Scheduled(fixedRate = 5000)
//    public void topicExchangeDemo3() {
//        rabbitTemplate.convertAndSend(Constants.TOPIC_EXCHANGE, "payment.confirmed", "confirmed payment");
//    }
}