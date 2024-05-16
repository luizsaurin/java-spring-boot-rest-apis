package com.example.mongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.mongo.advice.exceptions.NotFoundException;
import com.example.mongo.dto.UserCreationDto;
import com.example.mongo.dto.UserDetailsDto;
import com.example.mongo.dto.UserUpdateDto;
import com.example.mongo.model.User;
import com.example.mongo.repository.UserRepository;

@Service
public class UserService {
	
	// Dependencies

	@Autowired
	private UserRepository repository;

	// Methods

	public UserDetailsDto findById(String id) {
		return new UserDetailsDto(
			repository.findById(id).orElseThrow(() -> new NotFoundException())
		);
	}

	public Page<UserDetailsDto> findAll(Pageable pagination) {
		return repository.findAll(pagination).map(UserDetailsDto::new);
	}

	public UserDetailsDto create(UserCreationDto dto) {
		return new UserDetailsDto(repository.save(new User(dto)));
	}

	public UserDetailsDto update(String id, UserUpdateDto dto) {

		User user = repository.findById(id).orElseThrow(() -> new NotFoundException());
		
		user.setFirstName(dto.firstName());
		user.setLastName(dto.lastName());
		user.setAge(dto.age());
		user.setEmail(dto.email());
		user.setIsActive(dto.isActive());
		
		return new UserDetailsDto(repository.save(user));
	}
	
	public void delete(String id) {
		repository.findById(id).orElseThrow(() -> new NotFoundException());
		repository.deleteById(id);
	}
}
