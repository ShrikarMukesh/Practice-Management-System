package com.citiustech.allergies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AllergiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllergiesApplication.class, args);
	}

}
