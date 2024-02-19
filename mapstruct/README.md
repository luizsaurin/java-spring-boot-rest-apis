<h1 align="center"><strong>MapStruct</strong></h1>

<p>This is an example of an API using <a href="https://mapstruct.org/">MapStruct</a>. There are several other tools for mapping objects, MapStruct is one of the most popular due to its simplicity of use. Below will be detailed how it was implemented in this API.</p>

&nbsp;

<h3><strong>Project Configuration</strong></h3>

<p>Add the MapStruct dependencies to the pom.xml.</p>

```
<dependency>
	<groupId>org.mapstruct</groupId>
	<artifactId>mapstruct</artifactId>
	<version>1.5.5.Final</version>
</dependency>

<dependency>
	<groupId>org.mapstruct</groupId>
	<artifactId>mapstruct-processor</artifactId>
	<version>1.5.5.Final</version>
</dependency>
```

&nbsp;

<h3><strong>MapStruct Configuration</strong></h3>

<p>Create a MapperStruct interface. Naming the interface is open to you liking. In this example, I named MapStructMapper.</p>

```
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapStructMapper {

}
```

<p>The interface needs the @Mapper annotation so that MapStruct understands that the mapping contracts will be located here. Note that there are two settings: <b>componentModel</b> and <b>unmappedTargetPolicy.</b></p>

<p>Since this API is an application developed with Spring Boot, the <b>componentModel = "spring"</b> property allows us to use dependency injection with @Autowired on other components.</p>

<p>The <b>unmappedTargetPolicy</b> property is a way to configure how MapStruct will deal with fields that have not been explicitly mapped. In this case, I want fields not explicitly mapped to just be ignored. This will also remove warnings for these occurrences in your code editor.</p>

<p>MapStruct requires that all mappings that will be used in the API must be declared in the interface. In this example, there are only 3 mappings required:</p>

- User to UserDetailsDto
- UserCreationDto to User
- UserUpdateDto to User

```
User userUpdateDtoToUser(UserUpdateDto dto);

UserDetailsDto userToUserDetailsDto(User user);
```

<p>However, sometimes it is necessary to create custom mappings. To do this, it is possible to define the names of the source and destination fields if they are different, and also use a data converter if necessary.</p>

```
@Mapping(source = "enabled", target = "isActive", qualifiedByName = "stringToBoolean")
User userCreationDtoToUser(UserCreationDto dto);

@Named("stringToBoolean")
public static Boolean stringToBoolean(String str) {
  return Boolean.parseBoolean(str);
}
```
