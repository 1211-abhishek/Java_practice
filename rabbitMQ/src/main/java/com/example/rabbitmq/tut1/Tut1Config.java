package com.example.rabbitmq.tut1;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Tut1Config {

    @Bean
    public Queue dlQueue(){
        return new Queue(Constants.DL_QUEUE);
    }

    @Bean
    public Queue q1(){
        return QueueBuilder.durable(Constants.A_QUEUE)
                .withArgument("x-dead-letter-exchange",  Constants.DEAD_LETTER_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", "dlq-routing-key")
                .withArgument("x-message-ttl", 60000)
                .build();
    }

    @Bean
    public DirectExchange deadLetterExchange(){
        return new DirectExchange(Constants.DEAD_LETTER_EXCHANGE);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(Constants.TOPIC_EXCHANGE);
    }

    @Bean
    public Binding topicBinding1(Queue q1, TopicExchange topicExchange){
        return BindingBuilder.bind(q1).to(topicExchange).with("payment.*");
    }

    @Bean
    public Binding deadLetterBinding(Queue dlQueue, DirectExchange deadLetterExchange){
        return BindingBuilder.bind(dlQueue).to(deadLetterExchange).with("dlq-routing-key");
    }
}