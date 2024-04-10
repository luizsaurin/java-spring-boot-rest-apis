package com.example.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rabbitmq.component.RabbitMQProducer;
import com.example.rabbitmq.config.RabbitMQConfig;

@RestController
public class MainController {
	
	// Dependencies

	@Autowired
	private RabbitMQConfig mqConfig;
	
	@Autowired
	private RabbitMQProducer mqProducer;

	// Methods
	
	@PostMapping("/direct/{routingKey}")
	public ResponseEntity<?> postUsingDirectExchange(@PathVariable String routingKey) {
		mqProducer.sendMessage(mqConfig.getExchange01(), routingKey, "Hello world!");
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/fanout")
	public ResponseEntity<?> postUsingFanoutExchange() {
		mqProducer.sendMessage(mqConfig.getExchange02(), null, "Hello world!");
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/topic/{routingKey}")
	public ResponseEntity<?> postUsingTopicExchange(@PathVariable String routingKey) {
		mqProducer.sendMessage(mqConfig.getExchange03(), routingKey, "Hello world!");
		return ResponseEntity.ok().build();
	}

}
