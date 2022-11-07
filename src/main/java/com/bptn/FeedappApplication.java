package com.bptn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FeedappApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedappApplication.class, args);
	}

	//Spring executes the method and the method returns a RestTemplate object
	//because of the @Bean annotation. The RestTemplate object is allocated
	//to the Spring IoC container
	@Bean 
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
