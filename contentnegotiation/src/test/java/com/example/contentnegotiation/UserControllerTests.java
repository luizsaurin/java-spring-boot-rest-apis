package com.example.contentnegotiation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.contentnegotiation.constant.HeaderConstants;
import com.example.contentnegotiation.constant.MediaTypeConstants;
import com.example.contentnegotiation.constant.UserControllerURIs;
import com.example.contentnegotiation.controller.UserController;
import com.example.contentnegotiation.dto.UserCreateDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

@WebMvcTest(controllers = UserController.class)
@ExtendWith(MockitoExtension.class)
public class UserControllerTests {
	
	// Dependencies

	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	private YAMLMapper yamlMapper;
	private XmlMapper xmlMapper;
	
	// Attributes

	private UserCreateDTO userCreateDTO;

	// Setup

	@BeforeEach
	public void init() {
		userCreateDTO = new UserCreateDTO("John", "Doe", 41, "john.doe@mail.com", true);
		objectMapper = new ObjectMapper();
		yamlMapper = new YAMLMapper();
		xmlMapper = new XmlMapper();
	}

	// Tests

	@Test
	void findAll_JSON() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(UserControllerURIs.FIND_ALL)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
		;
	}

	@Test
	void findAll_XML() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(UserControllerURIs.FIND_ALL)
			.accept(MediaType.APPLICATION_XML))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(HeaderConstants.APPLICATION_XML_CHARSET_UTF8))
		;
	}

	@Test
	void findAll_YAML() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(UserControllerURIs.FIND_ALL)
			.accept(MediaType.valueOf(MediaTypeConstants.APPLICATION_YAML)))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.valueOf(MediaTypeConstants.APPLICATION_YAML)))
		;
	}

	@Test
	void create_JSON() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(UserControllerURIs.CREATE)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(userCreateDTO)))
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.header().exists(HeaderConstants.LOCATION))
		;
	}

	@Test
	void create_XML() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(UserControllerURIs.CREATE)
			.contentType(MediaType.APPLICATION_XML)
			.content(xmlMapper.writeValueAsString(userCreateDTO)))
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.header().exists(HeaderConstants.LOCATION))
		;
	}

	@Test
	void create_YAML() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(UserControllerURIs.CREATE)
			.contentType(MediaType.valueOf(MediaTypeConstants.APPLICATION_YAML))
			.content(yamlMapper.writeValueAsString(userCreateDTO)))
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.header().exists(HeaderConstants.LOCATION))
		;
	}

}
