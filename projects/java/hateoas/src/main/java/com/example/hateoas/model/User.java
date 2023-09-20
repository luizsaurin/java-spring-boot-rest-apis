package com.example.hateoas.model;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.example.hateoas.dto.UserCreationDto;
import com.example.hateoas.dto.UserUpdateDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "user")
@Table(name = "users")
public class User extends RepresentationModel<User> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private Integer age;
	private String email;
	private Boolean isActive;

	public User() {
	}

	public User(String firstName, String lastName, Integer age, String email, Boolean isActive) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.email = email;
		this.isActive = isActive;
	}

	public User(UserCreationDto dto) {
		this.firstName = dto.firstName();
		this.lastName = dto.lastName();
		this.age = dto.age();
		this.email = dto.email();
		this.isActive = dto.isActive();
	}

	public void update(UserUpdateDto dto) {
		this.firstName = dto.firstName();
		this.lastName = dto.lastName();
		this.age = dto.age();
		this.email = dto.email();
		this.isActive = dto.isActive();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

}
