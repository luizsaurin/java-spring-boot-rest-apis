package com.example.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
	
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private KafkaTemplate<String, String> template;

	public void send(String message) {
		log.info("Sending: " + message);
		template.send("hello-topic", message);
	}
}
