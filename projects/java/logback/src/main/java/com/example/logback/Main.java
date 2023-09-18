package com.example.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);

		Logger log = LoggerFactory.getLogger(Main.class);

		log.error("error message");
		log.warn("warning message");
		log.info("info message");
		log.debug("debug message");
	}

}
