package com.example.junit.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.junit.model.User;
import com.example.junit.repository.UserRepository;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTests {

	// Dependencies

	@Autowired
	private UserRepository userRepository;

	// Attributes

	private User testUser;

	// Methods

	@BeforeEach
	public void beforeEachTestSetup() {
		testUser = new User("John", "Doe", 40, "john.doe@mail.com", true);
	}

	@Test
	public void findById() {
		
		// Save user on database
		User savedUser = userRepository.save(testUser);
		
		// Search saved user by id
		Optional<User> optional = userRepository.findById(savedUser.getId());

		// Assertions
		assertTrue(optional.isPresent());
	}

	@Test
	public void findAll() {

		// Save user on database
		userRepository.save(testUser);

		// Search all users on database
		List<User> list = userRepository.findAll();

		// Assertions
		assertTrue(list.size() == 1);
	}

	@Test
	public void saveUser() {

		// Save user on database
		User savedUser = userRepository.save(testUser);

		// Assertions
		assertNotNull(savedUser);
	}
	
	@Test
	public void updateUser() {

		// Save user on database
		User originalUser = userRepository.save(testUser);

		// Update original user's first name
		String newFirstName = "Terry";
		originalUser.setFirstName(newFirstName);
		
		// Save updated user
		User updatedUser = userRepository.save(originalUser);
		
		// Assertions
		assertTrue(originalUser.getFirstName().contentEquals(testUser.getFirstName()));
		assertTrue(updatedUser.getFirstName().contentEquals(newFirstName));
		assertTrue(originalUser.getId() == updatedUser.getId());
	}

	@Test
	public void deleteUser() {

		// Save user on database
		User savedUser = userRepository.save(testUser);

		// Delete user
		userRepository.deleteById(savedUser.getId());
		
		// Search deleted user on database
		Optional<User> deletedUser = userRepository.findById(savedUser.getId());
		
		// Assertions
		assertTrue(deletedUser.isEmpty());
	}
	
}
