package com.example.swagger.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.swagger.dto.UserCreationDto;
import com.example.swagger.dto.UserDetailsDto;
import com.example.swagger.dto.UserUpdateDto;
import com.example.swagger.model.User;
import com.example.swagger.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired private UserRepository repository;

	public ResponseEntity<?> findById(Long id) {
		Optional<User> op = repository.findById(id);
		if(!op.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(new UserDetailsDto(op.get()));
	}

	public ResponseEntity<?> findAll(Pageable pagination) {
		
		Page<User> page = repository.findAll(pagination);

		List<User> content = page.getContent();

		if(content == null || content.size() <= 0) {
			return ResponseEntity.ok().build();
		}
		
		List<UserDetailsDto> users = new ArrayList<>();

		for (User u : content) {
			users.add(new UserDetailsDto(u));
		}
		
		return ResponseEntity.ok(users);
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
