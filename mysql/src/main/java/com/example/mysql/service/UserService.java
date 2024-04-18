package com.example.mysql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.mysql.advice.exceptions.NotFoundException;
import com.example.mysql.dto.UserCreationDto;
import com.example.mysql.dto.UserDetailsDto;
import com.example.mysql.dto.UserUpdateDto;
import com.example.mysql.model.User;
import com.example.mysql.repository.UserRepository;

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
