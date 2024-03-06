package com.example.modelmapper.config;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ModelMapperConverters.class)
public class ModelMapperConfig {

    @Bean
    ModelMapper modelMapper(AbstractConverter<?,?>[] converters) {

		ModelMapper mapper = new ModelMapper();

		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		mapper.getConfiguration().setFieldMatchingEnabled(true);
		mapper.getConfiguration().setFieldAccessLevel(AccessLevel.PRIVATE);
		
		for (AbstractConverter<?,?> converter : converters) {
			mapper.addConverter(converter);
		}

		return mapper;
	}

}
