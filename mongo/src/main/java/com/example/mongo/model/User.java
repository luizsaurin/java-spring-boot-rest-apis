package com.example.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.mongo.dto.UserCreationDto;

@Document(value = "user")
public class User {

	// Attributes

	@Id
	private String id;
	@Field(name = "first_name")
	private String firstName;
	@Field(name = "last_name")
	private String lastName;
	@Field(name = "age")
	private Integer age;
	@Field(name = "email")
	private String email;
	@Field(name = "is_active")
	private Boolean isActive;

	// Constructors

	public User() {
	}

	public User(UserCreationDto dto) {
		this.firstName = dto.firstName();
		this.lastName = dto.lastName();
		this.age = dto.age();
		this.email = dto.email();
		this.isActive = dto.isActive();
	}

	// Methods

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
