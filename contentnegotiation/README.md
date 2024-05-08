<h1 align="center"><strong>Content negotiation</strong></h1>

This project is an example of the implementation of Content Negotiation, that is, the API's ability to receive and return data in different formats like JSON, XML and YAML. 

The API is configured to receive requests and return responses containing body in JSON, XML and YAML formats. 

Unit tests were also implemented to test the Controller's methods.

Below are some points of attention:

&nbsp;

## Jackson Dependencies

Make sure that Jackson's dependencies for xml and yaml are included in pom.xml, as these are not included by default.

&nbsp;

## WebConfig

By default, the API is configured to handle JSON. In some cases it can even handle other formats, but depending on the situation errors may occur. Therefore, it is recommended that a configuration class be created, where the supported Content Types are manually defined. Check the implementation of the *WebConfig.java* class.

&nbsp;

## YAML Jackson Converter

In addition to the WebMvcConfigurer configuration, it is necessary to add the Jackson YAML converter configuration, which will be used in processing HTTP requests. This configuration was carried out in the *YamlJackson2HttpMessageConverter.java* class and included in the *WebConfig.java* class.

&nbsp;

## How to run

1. Run the API
1. Use the postman collection to test the endpoints. Folder: *resources/postman*.

## How to test

1. Run the tests. You can run the tests using the resources of your editor/IDE or via command line using:

```
mvn test
```

## **Dependencies**

- Docker
- Docker compose
- Java JDK 17
- Maven 3.9.3+
