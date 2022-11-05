package com.citiustech.procedure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProcedureApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcedureApplication.class, args);
	}

}
