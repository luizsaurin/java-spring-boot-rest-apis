package com.example.contentnegotiation.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.contentnegotiation.constant.MediaTypeConstants;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@SuppressWarnings("null")
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new YamlJackson2HttpMessageConverter());
	}

	@SuppressWarnings("null")
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer
			.favorParameter(false)
			.ignoreAcceptHeader(false)
			.useRegisteredExtensionsOnly(false)
			.defaultContentType(MediaType.APPLICATION_JSON)
			.mediaType(MediaTypeConstants.APPLICATION_JSON_SHORT, MediaType.APPLICATION_JSON)
			.mediaType(MediaTypeConstants.APPLICATION_XML_SHORT, MediaType.APPLICATION_XML)
			.mediaType(MediaTypeConstants.APPLICATION_YAML_SHORT, MediaType.valueOf(MediaTypeConstants.APPLICATION_YAML));
	}
}
