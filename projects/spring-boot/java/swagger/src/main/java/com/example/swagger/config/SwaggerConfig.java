package com.example.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
	
	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("My API using Swagger")
				.version("v1")
				.description("Simple Spring Boot REST API using Swagger")
				.termsOfService("")
				.license(
					new License()
						.name("MIT License")
						.url("https://opensource.org/license/mit/")));
	}
}
