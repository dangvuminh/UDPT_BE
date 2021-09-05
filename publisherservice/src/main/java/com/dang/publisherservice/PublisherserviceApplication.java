package com.dang.publisherservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PublisherserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublisherserviceApplication.class, args);
    }

}
