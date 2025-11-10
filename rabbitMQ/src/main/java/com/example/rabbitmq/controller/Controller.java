package com.example.rabbitmq.controller;

import com.example.rabbitmq.tut1.Tut1Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbitmq")
public class Controller {

    @Autowired
    private Tut1Sender tut1Sender;

    @PostMapping("/")
    public void placeOrder(){

        tut1Sender.sendOrder();
    }
}
