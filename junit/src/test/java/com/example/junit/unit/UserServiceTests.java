package com.example.junit.unit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.junit.advice.exceptions.NotFoundException;
import com.example.junit.dto.UserCreationDto;
import com.example.junit.dto.UserDetailsDto;
import com.example.junit.dto.UserUpdateDto;
import com.example.junit.model.User;
import com.example.junit.repository.UserRepository;
import com.example.junit.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

	// Dependencies

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	// Methods
	
	@Test
	public void findById_returnNonNullResponseDto() {
		
		// Mock
		when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(new User()));

		// Assertions
		assertNotNull(userService.findById(1L));
	}

	@Test
	public void findById_returnNotFoundException() {
		
		// Mock
		when(userRepository.findById(anyLong())).thenThrow(new NotFoundException());

		// Assertions
		assertThrows(NotFoundException.class, () -> userService.findById(1L));
	}

	@Test
	public void findAll_returnPageWithOneUser() {

		// Create parameter object for the service method
		Pageable pageSize1 = Pageable.ofSize(1);

		// Create response page object from the database query
		PageImpl<User> pageWithOneUser = new PageImpl<>(List.of(new User()));
		
		// Mock database query
		when(userRepository.findAll(pageSize1)).thenReturn(pageWithOneUser);

		// Assertions
		assertTrue(userService.findAll(pageSize1).getContent().size() == 1);
	}

	@Test
	public void createUser_returnResponseDtoWithCreatedUser() {

		// Create parameter object of the service method
		UserCreationDto dto = new UserCreationDto("John", "Doe", 40, "john.doe@mail.com", true);
		
		// Create user object from request DTO
		User user = new User(dto);

		// Mock database query
		when(userRepository.save(any(User.class))).thenReturn(user);

		// Call service method
		UserDetailsDto createdUser = userService.create(dto);

		// Assertions
		assertNotNull(createdUser);
	}

	@Test
	public void updateUser_returnResponseDtoWithUpdatedUser() {
		
		// User that already exists on the database
		User oldUser = new User();
		oldUser.setIsActive(true);
		
		// Mock findById query
		when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(oldUser));
		
		// Create update dto parameter
		UserUpdateDto userUpdateDto = new UserUpdateDto(null, null, null, null, false);
		
		// Create updated user, returned from the database insert query
		User updatedUser = new User();
		updatedUser.setIsActive(false);

		// Mock save query
		when(userRepository.save(any(User.class))).thenReturn(updatedUser);

		// Call service method
		UserDetailsDto responseDto = userService.update(1L, userUpdateDto);

		// Assertions
		assertFalse(responseDto.isActive());
	}

	@Test
	public void deleteUser_returnNoExceptions() {
		
		// Mock findById query
		when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(new User()));

		// Assertions
		assertAll(() -> userService.delete(1L));
	}

	@Test
	public void deleteUser_returnNotFoundException() {
		
		// Mock findById query
		when(userRepository.findById(anyLong())).thenThrow(new NotFoundException());

		// Assertions
		assertThrows(NotFoundException.class, () -> userService.delete(1L));
	}
}
