package com.example.junit.unit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.junit.advice.exceptions.NotFoundException;
import com.example.junit.controller.UserController;
import com.example.junit.dto.UserCreationDto;
import com.example.junit.dto.UserDetailsDto;
import com.example.junit.dto.UserUpdateDto;
import com.example.junit.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserControllerTests {
	
	// Dependencies

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Autowired
	private ObjectMapper objectMapper;

	// Attributes

	private UserDetailsDto detailsDto;
	private UserCreationDto creationDto;
	private UserUpdateDto updateDto;
	private UserDetailsDto updatedDetailsDto;

	// Methods

	@BeforeEach
	public void beforeEachTestSetup() {
		
		// init UserDetailsDto
		detailsDto = new UserDetailsDto(1L, "John", "Doe", 40, "john.doe@mail.com", true);

		// init UserCreationDto
		creationDto = new UserCreationDto("John", "Doe", 40, "john.doe@mail.com", true);

		// init UserUpdateDto
		updateDto = new UserUpdateDto("Tim", "Jones", 38, "tim.jones@mail.com", true);
		
		// init UserUpdateDto
		updatedDetailsDto = new UserDetailsDto(1L, "Tim", "Jones", 38, "tim.jones@mail.com", true);
	}

	@Test
	public void findById_200() throws Exception {

		// Mock
		when(userService.findById(anyLong())).thenReturn(detailsDto);

		// Request
		mockMvc.perform(
			get("/users/1"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(detailsDto.id()))
		;
	}

	@Test
	public void findById_404() throws Exception {

		// Mock
		when(userService.findById(anyLong())).thenThrow(new NotFoundException());

		// Request
		mockMvc.perform(
			get("/users/1"))
			.andExpect(MockMvcResultMatchers.status().isNotFound())
		;
	}

	@Test
	public void findAll_200() throws Exception {
		
		// Create pageable with one UserDetailsDto object
		PageImpl<UserDetailsDto> pageWithOneUser = new PageImpl<>(List.of(detailsDto));

		// Mock service
		when(userService.findAll(any(Pageable.class))).thenReturn(pageWithOneUser);

		// Request
		mockMvc.perform(
			get("/users"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.content.size()").value(pageWithOneUser.getSize()))
			.andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id").value(detailsDto.id()))
		;
	}

	@Test
	public void create_201() throws Exception {
		
		// Mock
		when(userService.create(any(UserCreationDto.class))).thenReturn(detailsDto);

		// Request
		mockMvc.perform(
			post("/users")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(creationDto)))
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.header().exists("Location"))
		;
	}
	
	@Test
	public void update_200() throws Exception {
		
		// Mock
		when(userService.update(anyLong(), any(UserUpdateDto.class))).thenReturn(updatedDetailsDto);

		// Request
		mockMvc.perform(
			put("/users/1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(updateDto)))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(updatedDetailsDto.id()))
			.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(updateDto.firstName()))
		;
	}
	
	@Test
	public void delete_204() throws Exception {

		// Mock service
		doNothing().when(userService).delete(anyLong());

		// Request
		mockMvc.perform(
			delete("/users/1"))
			.andExpect(MockMvcResultMatchers.status().isNoContent())
		;
	}
}
