package com.foody;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoodyJwtApplication {
	@SuppressWarnings("unused")
	private static final Logger logger = LogManager.getLogger(FoodyJwtApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(FoodyJwtApplication.class, args);
	}

}
