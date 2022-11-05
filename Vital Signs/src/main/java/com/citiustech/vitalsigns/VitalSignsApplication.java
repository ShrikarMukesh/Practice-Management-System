package com.citiustech.vitalsigns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class VitalSignsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VitalSignsApplication.class, args);
	}

}
