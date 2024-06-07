<h1 align="center"><strong>CORS</strong></h1>

This is an example of a Java Spring Boot API configured to handle CORS (Cross-Origin Resource Sharing). The project is very simplified, and there are several ways to configure CORS in the application. Some points of attention will be discussed below.

&nbsp;

## Configuration options

There are several ways to configure CORS in the application. Some ways are simpler, allowing you to make global settings for the entire application. Other ways are very specific and provide fine-grained control. Suggested reading: [CORS with Spring | Baeldung](https://www.baeldung.com/spring-cors).

&nbsp;

## Global configuration

For this project, I chose to apply a global configuration to the application, present in the *WebConfig.java* class. When implementing the *WebMvcConfigurer* interface, it is possible to override the *addCorsMappings* method. Inside this method, we can add CORS configurations.

```java
@Override
public void addCorsMappings(CorsRegistry registry) {
  registry.addMapping("/**")
    .allowedOrigins(corsOrigins.split(","))
    .allowedMethods("POST")
    .allowedHeaders("*");
}
```

This configuration reflects the following behaviors:

``addMapping("/**")`` = applies CORS to all application routes

``.allowedOrigins(corsOrigins.split(","))`` = defines allowed origin URLs

``.allowedMethods("POST")`` = only allow POST requests

``.allowedHeaders("*")`` = allow any headers

This way, the application will only allow POST requests coming from the URLs http://localhost:3000 and http://localhost:8080. Requests with other HTTP verbs and/or other origins will be denied.

These settings are only active if the *addCorsMappings* method is implemented. If the method is not implemented, or if it even exists but without any code inside, no CORS configuration will be active.

&nbsp;

## Browser vs. Non-Browser Requests

CORS is a security feature implemented by web browsers to prevent cross-origin requests initiated by client-side scripts. When you send a request using some HTTP Client (e.g., Postman, Insomnia) , it doesn't automatically include the Origin header because it's not a browser. Therefore, the API doesn't treat the request as a cross-origin request and doesn't enforce CORS restrictions. Therefore, when using any of these HTTP clients, make sure that the ``Origin`` header is present in the request. Otherwise, CORS restrictions will not trigger.

&nbsp;

## CORS with Spring Security

To configure CORS with the Spring Security module, the process is a little different. Spring Security has an out-of-the-box configuration, just add ``cors()`` to the *SecurityFilter* settings. If you want to configure manually, you can use *CorsConfigurationSource* @Bean. As this project is just a simple demonstration of CORS configuration without using Spring Security, these configurations were not applied. To learn more about it, I recommend reading: [CORS with Spring | Baeldung](https://www.baeldung.com/spring-cors).

&nbsp;

## **How to run**

1. Run the Spring Boot project
1. Use the postman collection on the *resources/postman* folder to test the API

&nbsp;

## **Dependencies**

- Java JDK 17
- Maven 3.9.3+
