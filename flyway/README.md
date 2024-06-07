<h1 align="center"><strong>Flyway</strong></h1>

This is an example of a Java Spring Boot API using [Flyway](https://flywaydb.org/) migration manager. Some points of attention regarding the implementation of this project will be discussed below:

&nbsp;

## Flyway dependency

Make sure the flyway dependency was correctly added to pom.xml

```xml
<dependency>
  <groupId>org.flywaydb</groupId>
  <artifactId>flyway-mysql</artifactId>
</dependency>
```

&nbsp;

## Migrations location

By default, flyway assumes that migrations are located at src/main/resources/db/migration. If the files are in another location, you must declare in application.properties (or application.yml):

```yaml
spring:
  flyway:
    locations: classpath:custom/migrations
```

In the case of this project, I chose to store the files in the default directory used by flyway, so this configuration was not necessary.

&nbsp;

## Migration nomenclature

Flyway supports several file formats, however in this project SQL migrations were used. By default, flyway requires a certain naming pattern in migrations. Check the [official documentation](https://documentation.red-gate.com/flyway/flyway-cli-and-api/concepts/migrations).

&nbsp;

## **How to run**

1. Run the docker-compose.yml to initialize the database
1. Run the Spring Boot project
1. Check the database and verify that the migrations ran correctly.

&nbsp;

## **Dependencies**

- Docker
- Docker compose
- Java JDK 17
- Maven 3.9.3+
