package com.dang.publisherservice.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${jsa.rabbitmq.exchange}")
    private String exchange;

    public void produceMsg(String msg){
        amqpTemplate.convertAndSend(exchange, "",msg);
    }
}
