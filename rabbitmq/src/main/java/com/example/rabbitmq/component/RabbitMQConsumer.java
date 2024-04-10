package com.example.rabbitmq.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

	// Dependencies

	Logger log = LoggerFactory.getLogger(this.getClass());

	// Methods
	
	@RabbitListener(queues = "${rabbitmq.queue.queue01}")
	public void queue01Listener(@Payload String message) {
		log.info("Received || Queue: queue01 || Message: " + message);
	}
	
	@RabbitListener(queues = "${rabbitmq.queue.queue02}")
	public void queue02Listener(@Payload String message) {
		log.info("Received || Queue: queue02 || Message: " + message);
	}
	
	@RabbitListener(queues = "${rabbitmq.queue.queue03}")
	public void queue03Listener(@Payload String message) {
		log.info("Received || Queue: queue03 || Message: " + message);
	}
	
	@RabbitListener(queues = "${rabbitmq.queue.queue04}")
	public void queue04Listener(@Payload String message) {
		log.info("Received || Queue: queue04 || Message: " + message);
	}
	
	@RabbitListener(queues = "${rabbitmq.queue.queue05}")
	public void queue05Listener(@Payload String message) {
		log.info("Received || Queue: queue05 || Message: " + message);
	}
	
	@RabbitListener(queues = "${rabbitmq.queue.queue06}")
	public void queue06Listener(@Payload String message) {
		log.info("Received || Queue: queue06 || Message: " + message);
	}
}
