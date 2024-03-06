package com.example.modelmapper.config;

import org.modelmapper.AbstractConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.modelmapper.dto.UserCreationDtoV2;
import com.example.modelmapper.model.User;

@Configuration
public class ModelMapperConverters {

    @Bean
    AbstractConverter<UserCreationDtoV2, User> userCreationDtoV2ToUser() {
        
		return new AbstractConverter<>() {
			
			@Override
			protected User convert(UserCreationDtoV2 source) {

				String[] nameParts = source.getName().split(" ");

				User user = new User();

				user.setFirstName(nameParts[0]);
				user.setLastName(nameParts[1]);
				user.setAge(source.getAge());
				user.setEmail(source.getEmail());
				user.setIsActive(Boolean.parseBoolean(source.getActive()));
				
				return user;
			}
		};
    }

}
