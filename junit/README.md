<h1 align="center"><strong>JUnit</strong></h1>

This is an example of a Java Spring Boot API that performs CRUD operations for Users and also has test classes. The main library for performing tests with Java is JUnit. In the case of this project, unit tests were developed with Mockito in the Controllers and Services layer, and integrated tests in the Repository layer. Some points of attention will be highlighted below.

&nbsp;

## **Test database**

In the case of integration tests, such as Repository layer classes, it is necessary to use a database. There are several options such as using an in-memory database, in a Docker container, or on a remote server, etc. This decision must be made considering the needs of your project and your team.

In the case of this project, I chose to use a MySQL database in a Docker container. To avoid using the application's main database, and possibly causing unwanted changes, I chose to carry out a configuration so that when running tests that use a database, a database identical to the main database is created, but only with the tables , without the inserts. The configuration consists of the following steps:

1. Create a unique application.properties (or .yml) for testing. In this case: application-test.yml.
1. This project uses Flyway migrations. Therefore, I stored the test bench migrations in src/test/resources/db/test-migration.
1. Indicate where the database migrations are located in application-test.yml.
1. Add the @ActiveProfiles("test") annotation to the test class that will use the database.

With this configuration, we were able to create a database with the same structure as the main application's database and use it in our tests.





<div align="center">
	<img src="">
</div>

&nbsp;

## **Hibernate logs**


If you want to disable hibernate logs during test execution, try using this configuration:

```
spring:
  jpa:
    properties:
      hibernate:
        '[show_sql]': false
```

&nbsp;

## **AutoConfigureTestDatabase**

Spring provides annotations to apply automatic configurations to test classes.

```
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
```

In this case, this annotation tells Spring that an external database will be used, not an in-memory database. This notation is generally applied to classes that will communicate with the database.

&nbsp;

## **AutoConfigureMockMvc**

```
@AutoConfigureMockMvc(addFilters = false)
```

Another configuration annotation is shown above. In this case, setting the addFilters setting to false causes Spring to disregard security filters. This configuration is generally used in Controller classes, where authentication methods are generally tested. And in this case, it was configured not to use filters.

&nbsp;

## **How to run - Main application**

1. Run the docker-compose.yml. This will start a container with a MySQL database.
1. Run the java project.
1. Use the postman collection to test the API. Is in the *resources/postman* folder.

&nbsp;


## **How to run - Tests**

1. Run the docker-compose.yml. This will start a container with a MySQL database.
1. Run the tests. You can run the tests using the resources of your editor/IDE or via command line using:

```
mvn test
```

&nbsp;

## **Dependencies**

- Docker
- Docker compose
- Java JDK 17
- Maven 3.9.3+