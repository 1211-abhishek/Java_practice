package com.example.rabbitmq.tut1;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import org.springframework.amqp.rabbit.annotation.RetryableTopic;

import java.io.IOException;

@Component
public class Tut1Receiver {

    @RabbitListener(queues = Constants.ORDER_QUEUE)
    public void orderListner(String message){

        System.out.println("Received message :" + message);
    }

    @RetryableTopic()
    @RabbitListener(queues = Constants.A_QUEUE)
    public void listenQueueA(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {

        System.out.println("A queue : " + message);
        channel.basicNack(tag,false,false);
    }
}