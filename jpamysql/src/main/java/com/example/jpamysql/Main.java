package com.example.jpamysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.jpamysql.repository.AuthorRepository;

@SpringBootApplication
public class Main implements CommandLineRunner {

	@Autowired
	private AuthorRepository authorRepository;

	Logger log = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String authorName = "Mary Beth";

		log.info("Author - Named query");
		log.info(authorRepository.findByName(authorName).toString());
		
		log.info("Author - JPQL query");
		log.info(authorRepository.findByNameJPQL(authorName).toString());
		
		log.info("Author - Native query");
		log.info(authorRepository.findByNameNative(authorName).toString());

	}
}
