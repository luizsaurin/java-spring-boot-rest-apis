package com.example.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mongo.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	@Query("{is_active: false}")
	List<User> findAllInactiveUsers();
}
