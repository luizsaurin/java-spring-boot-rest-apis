package com.example.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@KafkaListener(topics = "hello-topic", groupId = "group-01")
	public void receive(String message) {
		log.info("Received: " + message);
	}
}
