package com.dang.mailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {
    @Autowired
    private JavaMailSender javaMailSender;
    public void sendEmail(String toEmail,String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        message.setFrom("socialnetworkspringboot@gmail.com");
        javaMailSender.send(message);
    }
}
