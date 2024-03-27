package com.example.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	
	// Exchanges

	@Value("${rabbitmq.exchange.direct}")
	private String directExchange;
	@Value("${rabbitmq.exchange.fanout}")
	private String fanoutExchange;
	@Value("${rabbitmq.exchange.topic}")
	private String topicExchange;
	
	// Queues
	
	@Value("${rabbitmq.queue.queue01}")
	private String queue01;
	@Value("${rabbitmq.queue.queue02}")
	private String queue02;
	
	// Routing keys
	
	@Value("${rabbitmq.routing-key.orange}")
	private String routingKeyOrange;
	@Value("${rabbitmq.routing-key.black}")
	private String routingKeyBlack;
	@Value("${rabbitmq.routing-key.green}")
	private String routingKeyGreen;

	// Beans
	// // Exchanges

	@Bean
	DirectExchange directExchange() {
		return new DirectExchange(this.directExchange);
	}
	
	@Bean
	FanoutExchange fanoutExchange() {
		return new FanoutExchange(this.fanoutExchange);
	}
	
	@Bean
	TopicExchange topicExchange() {
		return new TopicExchange(this.topicExchange);
	}

	// // Queues

	@Bean
	Queue queue01() {
		return new Queue(this.queue01);
	}

	@Bean
	Queue queue02() {
		return new Queue(this.queue02);
	}

	// // Bindings

	@Bean
	Binding binding01() {
		return BindingBuilder.bind(queue01()).to(directExchange()).with(this.routingKeyOrange);
	}
	
	@Bean
	Binding binding02() {
		return BindingBuilder.bind(queue02()).to(directExchange()).with(this.routingKeyBlack);
	}
	
	@Bean
	Binding binding03() {
		return BindingBuilder.bind(queue02()).to(directExchange()).with(this.routingKeyGreen);
	}

	// // Getters

	public String getDirectExchange() {
		return directExchange;
	}

	public String getFanoutExchange() {
		return fanoutExchange;
	}

	public String getTopicExchange() {
		return topicExchange;
	}

	public String getQueue01() {
		return queue01;
	}

	public String getQueue02() {
		return queue02;
	}

	public String getRoutingKeyOrange() {
		return routingKeyOrange;
	}

	public String getRoutingKeyBlack() {
		return routingKeyBlack;
	}

	public String getRoutingKeyGreen() {
		return routingKeyGreen;
	}
	
}
