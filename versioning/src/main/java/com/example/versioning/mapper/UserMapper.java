package com.example.versioning.mapper;

import org.springframework.stereotype.Component;

import com.example.versioning.dto.v1.UserCreationDtoV1;
import com.example.versioning.dto.v1.UserDetailsDtoV1;
import com.example.versioning.dto.v2.UserCreationDtoV2;
import com.example.versioning.dto.v2.UserDetailsDtoV2;
import com.example.versioning.model.User;

@Component
public class UserMapper {
	
	// From User to someObject mappers

	public UserDetailsDtoV1 toUserDetailsDtoV1(User user) {
		return new UserDetailsDtoV1(user.getId(), user.getName());
	}

	public UserDetailsDtoV2 toUserDetailsDtoV2(User user) {
		return new UserDetailsDtoV2(user.getId(), user.getName(), user.getEmail());
	}

	// From someObject to User mappers

	public User toUser(UserCreationDtoV1 dto) {

		User user = new User();
		user.setName(dto.name());

		return user;
	}

	public User toUser(UserCreationDtoV2 dto) {

		User user = new User();
		user.setName(dto.name());
		user.setEmail(dto.email());

		return user;
	}
	
}
