package com.foody;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FoodyJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodyJwtApplication.class, args);
	}

}
