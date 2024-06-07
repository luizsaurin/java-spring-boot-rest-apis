<h1 align="center"><strong>MapStruct</strong></h1>

This is an example of an API using [MapStruct]("https://mapstruct.org/"). There are several other tools for mapping objects, MapStruct is one of the most popular due to its simplicity of use. Below will be detailed how it was implemented in this API.

&nbsp;

### **Project Configuration**

Add the MapStruct dependencies to the pom.xml.

```xml
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

### **MapStruct Configuration**

Create a MapperStruct interface. Naming the interface is open to you liking. In this example, I named MapStructMapper.

```java
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapStructMapper {

}
```

The interface needs the @Mapper annotation so that MapStruct understands that the mapping contracts will be located here. Note that there are two settings: **componentModel** and **unmappedTargetPolicy.**

Since this API is an application developed with Spring Boot, the **componentModel = "spring"** property allows us to use dependency injection with @Autowired on other components.

The **unmappedTargetPolicy** property is a way to configure how MapStruct will deal with fields that have not been explicitly mapped. In this case, I want fields not explicitly mapped to just be ignored. This will also remove warnings for these occurrences in your code editor.

MapStruct requires that all mappings that will be used in the API must be declared in the interface. In this example, there are only 3 mappings required:

- User to UserDetailsDto
- UserCreationDto to User
- UserUpdateDto to User

```java
User userUpdateDtoToUser(UserUpdateDto dto);

UserDetailsDto userToUserDetailsDto(User user);
```

However, sometimes it is necessary to create custom mappings. To do this, it is possible to define the names of the source and destination fields if they are different, and also use a data converter if necessary.

```java
@Mapping(source = "enabled", target = "isActive", qualifiedByName = "stringToBoolean")
User userCreationDtoToUser(UserCreationDto dto);

@Named("stringToBoolean")
public static Boolean stringToBoolean(String str) {
  return Boolean.parseBoolean(str);
}
```

&nbsp;

## **Dependencies**

- Java JDK 17
- Maven 3.9.3+