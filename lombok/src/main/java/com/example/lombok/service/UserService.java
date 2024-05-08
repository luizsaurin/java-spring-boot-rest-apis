package com.example.lombok.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.lombok.advice.exceptions.NotFoundException;
import com.example.lombok.dto.UserCreationDto;
import com.example.lombok.dto.UserDetailsDto;
import com.example.lombok.dto.UserUpdateDto;
import com.example.lombok.model.User;
import com.example.lombok.repository.UserRepository;

@Service
public class UserService {
	
	// Dependencies

	@Autowired
	private UserRepository userRepository;

	// Methods

	public UserDetailsDto findById(Long id) {
		return new UserDetailsDto(
			userRepository.findById(id).orElseThrow(() -> new NotFoundException())
		);
	}

	public Page<UserDetailsDto> findAll(Pageable pagination) {
		return userRepository.findAll(pagination).map(UserDetailsDto::new);
	}

	public UserDetailsDto create(UserCreationDto dto) {
		return new UserDetailsDto(userRepository.save(new User(dto)));
	}

	public UserDetailsDto update(Long id, UserUpdateDto dto) {

		User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException());
		
		user = User.builder()
			.id(user.getId())
			.firstName(dto.getFirstName())
			.lastName(dto.getLastName())
			.age(dto.getAge())
			.email(dto.getEmail())
			.isActive(dto.getIsActive())
		.build();

		return new UserDetailsDto(userRepository.save(user));
	}
	
	public void delete(Long id) {
		userRepository.findById(id).orElseThrow(() -> new NotFoundException());
		userRepository.deleteById(id);
	}
}
