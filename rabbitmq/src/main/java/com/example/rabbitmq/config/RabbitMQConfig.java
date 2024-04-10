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

	@Value("${rabbitmq.exchange.exchange01}")
	private String exchange01;
	@Value("${rabbitmq.exchange.exchange02}")
	private String exchange02;
	@Value("${rabbitmq.exchange.exchange03}")
	private String exchange03;
	
	// Queues
	
	@Value("${rabbitmq.queue.queue01}")
	private String queue01;
	@Value("${rabbitmq.queue.queue02}")
	private String queue02;
	@Value("${rabbitmq.queue.queue03}")
	private String queue03;
	@Value("${rabbitmq.queue.queue04}")
	private String queue04;
	@Value("${rabbitmq.queue.queue05}")
	private String queue05;
	@Value("${rabbitmq.queue.queue06}")
	private String queue06;
	
	// Routing keys
	
	@Value("${rabbitmq.routing-key.key01}")
	private String routingKey01;
	@Value("${rabbitmq.routing-key.key02}")
	private String routingKey02;
	@Value("${rabbitmq.routing-key.key03}")
	private String routingKey03;
	@Value("${rabbitmq.routing-key.key04}")
	private String routingKey04;
	@Value("${rabbitmq.routing-key.key05}")
	private String routingKey05;
	@Value("${rabbitmq.routing-key.key06}")
	private String routingKey06;

	// Beans
	// // Exchanges

	@Bean
	DirectExchange exchange01() {
		return new DirectExchange(this.exchange01);
	}
	
	@Bean
	FanoutExchange exchange02() {
		return new FanoutExchange(this.exchange02);
	}
	
	@Bean
	TopicExchange exchange03() {
		return new TopicExchange(this.exchange03);
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
	
	@Bean
	Queue queue03() {
		return new Queue(this.queue03);
	}
	
	@Bean
	Queue queue04() {
		return new Queue(this.queue04);
	}
	
	@Bean
	Queue queue05() {
		return new Queue(this.queue05);
	}
	
	@Bean
	Queue queue06() {
		return new Queue(this.queue06);
	}

	// // Bindings

	@Bean
	Binding binding01() {
		return BindingBuilder.bind(queue01()).to(exchange01()).with(this.routingKey01);
	}
	
	@Bean
	Binding binding02() {
		return BindingBuilder.bind(queue02()).to(exchange01()).with(this.routingKey02);
	}
	
	@Bean
	Binding binding03() {
		return BindingBuilder.bind(queue02()).to(exchange01()).with(this.routingKey03);
	}
	
	@Bean
	Binding binding04() {
		return BindingBuilder.bind(queue03()).to(exchange02());
	}
	
	@Bean
	Binding binding05() {
		return BindingBuilder.bind(queue04()).to(exchange02());
	}
	
	@Bean
	Binding binding06() {
		return BindingBuilder.bind(queue05()).to(exchange03()).with(this.routingKey04);
	}
	
	@Bean
	Binding binding07() {
		return BindingBuilder.bind(queue06()).to(exchange03()).with(this.routingKey05);
	}
	
	@Bean
	Binding binding08() {
		return BindingBuilder.bind(queue06()).to(exchange03()).with(this.routingKey06);
	}

	// // Getters

	public String getExchange01() {
		return exchange01;
	}

	public String getExchange02() {
		return exchange02;
	}

	public String getExchange03() {
		return exchange03;
	}

	public String getQueue01() {
		return queue01;
	}

	public String getQueue02() {
		return queue02;
	}

	public String getQueue03() {
		return queue03;
	}

	public String getQueue04() {
		return queue04;
	}

	public String getQueue05() {
		return queue05;
	}

	public String getQueue06() {
		return queue06;
	}

	public String getRoutingKey01() {
		return routingKey01;
	}

	public String getRoutingKey02() {
		return routingKey02;
	}

	public String getRoutingKey03() {
		return routingKey03;
	}

	public String getRoutingKey04() {
		return routingKey04;
	}

	public String getRoutingKey05() {
		return routingKey05;
	}

	public String getRoutingKey06() {
		return routingKey06;
	}

}
