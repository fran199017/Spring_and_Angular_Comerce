package com.francisconicolau;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableAutoConfiguration
public class ComerceApplication {

	private static final Logger log = LoggerFactory.getLogger(ComerceApplication.class);
	public static void main(String[] args) {
		log.info("Empiezo la app ComerceAPI");
		SpringApplication.run(ComerceApplication.class, args);
	}

}
