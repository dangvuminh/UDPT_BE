package com.dang.tagservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TagserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TagserviceApplication.class, args);
	}

}
