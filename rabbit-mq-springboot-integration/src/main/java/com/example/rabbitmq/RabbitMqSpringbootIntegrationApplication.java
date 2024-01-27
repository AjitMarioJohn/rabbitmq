package com.example.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class RabbitMqSpringbootIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitMqSpringbootIntegrationApplication.class, args);
	}

}
