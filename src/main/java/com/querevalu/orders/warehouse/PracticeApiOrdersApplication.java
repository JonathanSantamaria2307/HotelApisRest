package com.querevalu.orders.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PracticeApiOrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeApiOrdersApplication.class, args);
	}

}
