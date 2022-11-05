package com.citiustech.medication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MedicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicationApplication.class, args);
	}

}
