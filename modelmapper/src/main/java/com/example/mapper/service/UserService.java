package com.example.mapper.service;

import java.net.URI;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.mapper.dto.UserCreationDtoV1;
import com.example.mapper.dto.UserCreationDtoV2;
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
	}

	public ResponseEntity<?> findAll(Pageable pagination) {

		return ResponseEntity.ok(repository.findAll(pagination).getContent().stream()
			.map(user -> mapper.map(user, UserDetailsDto.class))
			.collect(Collectors.toList())
		);

	}

	public ResponseEntity<?> create(UserCreationDtoV1 dto, UriComponentsBuilder uriBuilder) {
		
		User user = repository.save(mapper.map(dto, User.class));

		URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.created(uri).body(mapper.map(user, UserDetailsDto.class));
	}

	public ResponseEntity<?> create(UserCreationDtoV2 dto, UriComponentsBuilder uriBuilder) {

		User user = repository.save(mapper.map(dto, User.class));

		URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.created(uri).body(mapper.map(user, UserDetailsDto.class));
	}

	public ResponseEntity<?> update(Long id, UserUpdateDto dto) {
		
		Optional<User> op = repository.findById(id);
		
		if(op.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		User userToUpdate = mapper.map(dto, User.class);
		userToUpdate.setId(id);
		
		return ResponseEntity.ok(mapper.map(repository.save(userToUpdate), UserDetailsDto.class));
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
