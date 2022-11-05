package com.citiustech.diagnosis.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	@Bean 
	public Docket swaggerConfiguration() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				          .select()
				          .apis(RequestHandlerSelectors.basePackage("com.citiustech.diagnosis"))
				          .paths(PathSelectors.regex("/diagnosis.*"))				         
				         .build()
				         .apiInfo(apiInformation());
	}
	
	public ApiInfo apiInformation() {
		return new ApiInfo("Diagnosis MicroService", 
				                      "Diagnosis MicroService for PMS", 
				                      "1.0", 
				                      "http://swagger.io/terms/",
				                      new Contact("Java Team 3", "http://www.citiustech.com/", "ctshrikar@gmail.com"), 
				                      "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0.html", Collections.emptyList());		
	}
}











