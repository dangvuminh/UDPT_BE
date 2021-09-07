package com.dang.publisherservice.controller;

import com.dang.publisherservice.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mail")
public class PubController {

    @Autowired
    PublisherService publisherService;

    @GetMapping("/sendEmail")
    public String sendMsg(@RequestParam("msg")String msg){
        publisherService.produceMsg(msg);
        return "Done";
    }
}