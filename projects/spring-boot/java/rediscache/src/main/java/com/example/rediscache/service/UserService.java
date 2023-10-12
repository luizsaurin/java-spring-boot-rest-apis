package com.example.rediscache.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.rediscache.dto.UserCreationDto;
import com.example.rediscache.dto.UserDetailsDto;
import com.example.rediscache.dto.UserUpdateDto;
import com.example.rediscache.model.User;
import com.example.rediscache.repository.UserRepository;

@Service
public class UserService {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired private UserRepository repository;

	@Cacheable(value = "users", key = "#id", unless = "#result == null")
	public UserDetailsDto findById(Long id) {

		log.info("findById");
		
		Optional<User> op = repository.findById(id);
		if(!op.isPresent()) {
			return null;
		}
		return new UserDetailsDto(op.get());
	}
	
	public Page<UserDetailsDto> findAll(Pageable pagination) {
		return repository.findAll(pagination).map(UserDetailsDto::new);
	}
	
	public User create(UserCreationDto dto) {
		
		log.info("create");
		
		return repository.save(new User(dto));
	}
	
	@CachePut(value = "users", key = "#id", unless = "#result == null")
	public UserDetailsDto update(Long id, UserUpdateDto dto) {
		
		log.info("update");
		
		Optional<User> op = repository.findById(id);
		if(!op.isPresent()) {
			return null;
		}
		User user = op.get();
		user.update(dto);
		repository.save(user);
		return new UserDetailsDto(user);
	}

	@CacheEvict(value = "users", key = "#id")
	public boolean delete(Long id) {

		log.info("delete");
		
		Optional<User> op = repository.findById(id);
		if(!op.isPresent()) {
			return false;
		}
		repository.deleteById(id);
		return true;
	}
}
