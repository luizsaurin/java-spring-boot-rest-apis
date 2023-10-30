package com.example.mapper.service;

import java.net.URI;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.mapper.dto.UserCreationDto;
import com.example.mapper.dto.UserDetailsDto;
import com.example.mapper.dto.UserUpdateDto;
import com.example.mapper.model.User;
import com.example.mapper.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired private UserRepository repository;
	@Autowired private ModelMapper mapper;

	public ResponseEntity<?> findById(Long id) {
		Optional<User> op = repository.findById(id);
		if(!op.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(mapper.map(op.get(), UserDetailsDto.class));
		// return ResponseEntity.ok(new UserDetailsDto(op.get()));
	}

	public ResponseEntity<?> findAll(Pageable pagination) {
		return ResponseEntity.ok(repository.findAll(pagination).map(UserDetailsDto::new));
	}

	public ResponseEntity<?> create(UserCreationDto dto, UriComponentsBuilder uriBuilder) {
		User user = repository.save(new User(dto));
		URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(new UserDetailsDto(user));
	}

	public ResponseEntity<?> update(Long id, UserUpdateDto dto) {
		Optional<User> op = repository.findById(id);
		if(!op.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		User user = op.get();
		user.update(dto);
		repository.save(user);
		return ResponseEntity.ok(new UserDetailsDto(user));
	}

	public ResponseEntity<?> delete(Long id) {
		Optional<User> op = repository.findById(id);
		if(!op.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
