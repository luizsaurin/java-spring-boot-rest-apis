<h1 align="center"><strong>ModelMapper</strong></h1>

<p>This is an example of an API using <a href="https://modelmapper.org/">ModelMapper</a>. There are several other tools for mapping objects, ModelMapper is one of the most popular due to its simplicity of use. Below will be detailed how it was implemented in this API.</p>

<p>❗❗ Note: until this moment, ModelMapper does not work with Records from Java 17. It works with conventional java classes, optionally with lombok.</p>

&nbsp;

<h3><strong>Initial Configuration</strong></h3>

<p>First you need to create the @Bean with the ModelMapper configuration.</p>
<p>Add the modelmapper dependency in the pom.xml.</p>

```
<dependency>
  <groupId>org.modelmapper</groupId>
  <artifactId>modelmapper</artifactId>
  <version>3.2.0</version> <!-- latest version -->
</dependency>
```

<p>Create a the initial Configuration class.</p>

```
@Configuration
public class ModelMapperConfig {
  @Bean
  ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
```

&nbsp;

<h3><strong>Direct Mapping</strong></h3>

<p>Using the initial configuration above, it's already possible to use modelmapper. Take a look at the example below:</p>

```
UserDetailsDto dto = mapper.map(User.class, UserDetailsDto.class);
```

<p>The User.class is the source object, and the UserDetailsDto.class is the destination object. If the conversion is successful, it will create a UserDetailsDto object with the User class informations.</p>

<p>This direct mapping is possible when the objects have the same structure.</p>

<div align="center">
	<img src="resources/img/direct-mapping.png">
</div>

&nbsp;

<h3><strong>Custom Mapping</strong></h3>

<p>Sometimes the source and destination objects have different structures, and for this it is necessary to create a customized mapping. There are several ways to do this, the way I chose was to use Converters.</p>

<p>In this first example, I want to convert the UserCreationDtoV1 object to a User object. Since they have the same structure, it's possible to use a direct mapping.</p>

<div align="center">
	<img src="resources/img/custom-1.png">
</div>

<p>Now on the second example, I want to convert the UserCreationDtoV2 object to a User object, but they have different structures.</p>

<div align="center">
	<img src="resources/img/custom-2.png">
</div>

<p>There are three points of attention</p>

- The ID attribute is not present in UserCreation toV2. I chose to resolve this in the UserService class, so it will not be the Modelmapper's responsibility.
- The "name" attribute on UserCreationDtoV2 must be divided into two parts and stored in the "firstName" and "lastName" attributes of the User object.
- The "active" attribute on UserCreationDtoV2 needs to be converted from String to Boolean, and be stored on the "isActive" attribute of the User object.

<p>At run time, ModelMapper will try to map the two objects, but it will not be done correctly, as ModelMapper does not yet know how to resolve the incompatibilities between these two objects. Therefore, let's create a custom converter, showing ModelMapper how the mapping of these two objects should be carried out when requested.</p>

<p>First, let's create a class where all the converters in our API will be stored, in this case it will only be one.</p>

<div align="center">
	<img src="resources/img/converter-1.png">
</div>

<p>The converter has been created but is not yet used. It is necessary to modify the ModelMapper configuration class to read and use all converters created here.</p>

<div align="center">
	<img src="resources/img/custom-converters-config.png">
</div>

<p>From now on, every time a conversion from UserCreationDtoV2 to User is performed, it will check if there is a custom converter and will use it if it exists..</p>
