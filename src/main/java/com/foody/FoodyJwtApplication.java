package com.foody;

//import java.util.HashSet;
//import java.util.Set;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		FoodyJwtApplication.class,
		Jsr310JpaConverters.class
})
public class FoodyJwtApplication{
	@SuppressWarnings("unused")
	private static final Logger logger = LogManager.getLogger(FoodyJwtApplication.class);
	
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
	
	public static void main(String[] args) {
		SpringApplication.run(FoodyJwtApplication.class, args);
	}

	
}
