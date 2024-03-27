package com.example.rabbitmq.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducer {
	
	// Dependencies

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	Logger log = LoggerFactory.getLogger(this.getClass());

	// Methods

	public void sendMessage(String exchange, String routingKey, String message) {
		log.info("Sending || Exchange: " + exchange + " || Routing Key: " + routingKey + " || Message: " + message);
		amqpTemplate.convertAndSend(exchange, routingKey, message);
	}
}
