<h1 align="center"><strong>Liquibase</strong></h1>

This is an example of a Java Spring Boot API using the [Liquibase](https://docs.liquibase.com/home.html) migration manager. Some points of attention regarding the implementation of this project will be discussed below:

&nbsp;

## Liquibase dependency

Make sure the liquibase dependency was correctly added to pom.xml

```
<dependency>
  <groupId>org.liquibase</groupId>
  <artifactId>liquibase-core</artifactId>
</dependency>
```

&nbsp;

## changelog.xml

Liquibase requires the existence of the changelog.xml file. The main function of this file is to define the migration strategy.

The simplest strategy is to run all migrations present in a given directory.

```
<includeAll path="liquibase/changesets"/>
```

However, it is recommended to define the order in which each migration should be performed.

```
<include relativeToChangelogFile="true" file="changesets/V1__create-user-table.sql" />
```

&nbsp;

## changelog.xml location


Within the application.properties (or application.yml) file, in addition to the datasource settings, it is necessary to inform the location of the changelog.xml file. In this case, I stored the file in *src/main/resources/liquibase/*. The storage location is a free choice, I chose this path.

```
spring:
  liquibase:
    change-log: classpath:/liquibase/changelog.xml
```

&nbsp;

## File and folder naming

In general, liquibase does not require a standard for file and folder naming. The name of the folder where the changelog.xml is located, the name of the migrations... all free for the user to choose.

A scenario where this is taken into consideration is when the migration application strategy, defined in the changelog.xml, is ``includeAll``. In this case, the migrations will be applied according to the ordering by the name of each file. To avoid possible problems, it is recommended to define a migration naming pattern and define the application order of each migration in the changelog.xml using ``include``, in the same way as it was configured in this project.

&nbsp;

## Migration format

Liquibase supports different types of file formats such as XML, SQL, YAML, JSON, etc. In the case of this project, SQL was used.

Generally, migrations only have the code to be executed. However, liquibase allows you to add metadata to the migration code.

```
--liquibase formatted sql

--changeset author:id
--comment:  insert comment here
	>> SQL CODE HERE <<
--rollback insert SQL rollback code here;
```

This helps in understanding the code and context in which the migration was created. See the [official documentation](https://docs.liquibase.com/concepts/changelogs/sql-format.html) to learn more.

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