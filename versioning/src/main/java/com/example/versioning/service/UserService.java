package com.example.versioning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.versioning.advice.exceptions.NotFoundException;
import com.example.versioning.dto.v1.UserCreationDtoV1;
import com.example.versioning.dto.v1.UserDetailsDtoV1;
import com.example.versioning.dto.v2.UserCreationDtoV2;
import com.example.versioning.dto.v2.UserDetailsDtoV2;
import com.example.versioning.mapper.UserMapper;
import com.example.versioning.model.User;
import com.example.versioning.repository.UserRepository;

@Service
public class UserService {
	
	// Dependencies

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;

	// Methods

	public UserDetailsDtoV1 findByIdV1(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException());
		return userMapper.toUserDetailsDtoV1(user);
	}

	public UserDetailsDtoV2 findByIdV2(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException());
		return userMapper.toUserDetailsDtoV2(user);
	}

	public Page<UserDetailsDtoV1> findAllV1(Pageable pagination) {
		return userRepository.findAll(pagination).map(userMapper::toUserDetailsDtoV1);
	}

	public Page<UserDetailsDtoV2> findAllV2(Pageable pagination) {
		return userRepository.findAll(pagination).map(userMapper::toUserDetailsDtoV2);
	}

	public UserDetailsDtoV1 createV1(UserCreationDtoV1 dto) {
		return userMapper.toUserDetailsDtoV1(userRepository.save(userMapper.toUser(dto)));
	}

	public UserDetailsDtoV2 createV2(UserCreationDtoV2 dto) {
		return userMapper.toUserDetailsDtoV2(userRepository.save(userMapper.toUser(dto)));
	}

}
