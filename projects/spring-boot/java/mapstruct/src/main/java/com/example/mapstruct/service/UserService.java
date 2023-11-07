package com.example.mapstruct.service;

import java.net.URI;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.mapstruct.dto.UserCreationDto;
import com.example.mapstruct.dto.UserUpdateDto;
import com.example.mapstruct.mapstruct.MapStructMapper;
import com.example.mapstruct.model.User;
import com.example.mapstruct.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired private UserRepository repository;
	@Autowired private MapStructMapper mapper;

	public ResponseEntity<?> findById(Long id) {
		Optional<User> op = repository.findById(id);
		if(!op.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(mapper.userToUserDetailsDto(op.get()));
	}

	public ResponseEntity<?> findAll(Pageable pagination) {
		return ResponseEntity.ok(repository.findAll(pagination).getContent().stream()
			.map(user -> mapper.userToUserDetailsDto(user))
			.collect(Collectors.toList())
		);
	}

	public ResponseEntity<?> create(UserCreationDto dto, UriComponentsBuilder uriBuilder) {
		User user = repository.save(mapper.userCreationDtoToUser(dto));
		URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(mapper.userToUserDetailsDto(user));
	}

	public ResponseEntity<?> update(Long id, UserUpdateDto dto) {
		Optional<User> op = repository.findById(id);
		if(!op.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		User userToUpdate = mapper.userUpdateDtoToUser(dto);
		userToUpdate.setId(id);

		return ResponseEntity.ok(mapper.userToUserDetailsDto(repository.save(userToUpdate)));
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
