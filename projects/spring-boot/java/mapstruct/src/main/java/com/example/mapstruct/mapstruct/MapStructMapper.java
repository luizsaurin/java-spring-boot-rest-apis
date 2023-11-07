package com.example.mapstruct.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.example.mapstruct.dto.UserCreationDto;
import com.example.mapstruct.dto.UserDetailsDto;
import com.example.mapstruct.dto.UserUpdateDto;
import com.example.mapstruct.model.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapStructMapper {

	//----------- Contracts -----------

	@Mapping(source = "name", target = "firstName", qualifiedByName = "getFirstNameFromFullName")
	@Mapping(source = "name", target = "lastName", qualifiedByName = "getLastNameFromFullName")
	@Mapping(source = "enabled", target = "isActive", qualifiedByName = "stringToBoolean")
	User userCreationDtoToUser(UserCreationDto dto);
	
	User userUpdateDtoToUser(UserUpdateDto dto);

	UserDetailsDto userToUserDetailsDto(User user);
	
	//----------- Converters -----------

	@Named("stringToBoolean")
	public static Boolean stringToBoolean(String str) {
		return Boolean.parseBoolean(str);
	}

	@Named("getFirstNameFromFullName")
	public static String getFirstNameFromFullName(String str) {
		return str.split(" ")[0];
	}

	@Named("getLastNameFromFullName")
	public static String getLastNameFromFullName(String str) {
		return str.split(" ")[1];
	}
}
