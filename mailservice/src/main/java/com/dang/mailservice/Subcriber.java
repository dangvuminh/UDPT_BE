package com.dang.mailservice;

import com.dang.mailservice.entity.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Subcriber {
    @Autowired
    private SendMailService sendMailService;
    @RabbitListener(queues="${jsa.rabbitmq.queue}")
    public void recievedMessage(String msg) {
       // System.out.println("Recieved Message: " + msg);
        String[] parts = msg.split(",");
       sendMailService.sendEmail(parts[0],parts[1],parts[2]);
    }
}
