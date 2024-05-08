package com.example.flyway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.flyway.advice.exceptions.NotFoundException;
import com.example.flyway.dto.UserCreationDto;
import com.example.flyway.dto.UserDetailsDto;
import com.example.flyway.dto.UserUpdateDto;
import com.example.flyway.model.User;
import com.example.flyway.repository.UserRepository;

@Service
public class UserService {
	
	// Dependencies

	@Autowired
	private UserRepository repository;

	// Methods

	public UserDetailsDto findById(Long id) {
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

	public UserDetailsDto update(Long id, UserUpdateDto dto) {

		User user = repository.findById(id).orElseThrow(() -> new NotFoundException());
		
		user.setFirstName(dto.firstName());
		user.setLastName(dto.lastName());
		user.setAge(dto.age());
		user.setEmail(dto.email());
		user.setIsActive(dto.isActive());
		
		return new UserDetailsDto(repository.save(user));
	}
	
	public void delete(Long id) {
		repository.findById(id).orElseThrow(() -> new NotFoundException());
		repository.deleteById(id);
	}
}
