<h1 align="center"><strong>MongoDB</strong></h1>

This is an example of a Java Spring Boot API using the MongoDB database. Some points of attention will be detailed below:

&nbsp;

## **application config**

Unlike SQL databases, Mongo does not use ``datasource`` properties in application.yml. To connect, the following information is required:

- host
- port
- database
- username
- password

These variables can be concatenated using the uri property, or declared separately as performed in this project.

If the connection requires authentication, it is also necessary to declare the ``authentication-database`` property. The value of this property is the name of the collection where the login information to be used is stored. By default, the root user is stored in the **admin** collection.

&nbsp;

## **database and collection startup**

It is not necessary to create the database and a collection before using the API. The moment the first transactional request is executed (create, update, delete,...), the necessary database and collections will be automatically created.

&nbsp;

## **mongo-express**

There are tools with a graphical interface to facilitate the use of the database. A popular option is MongoDBCompass. However, there is the alternative mongo-express, which can be run in a Docker container. In this case, it is important to remember that when using this tool, it is necessary to configure mongodb and mongo-express so that they run within the same network.

&nbsp;

## **How to run**

1. Run the docker-compose.yml to initialize the database
1. Run the Spring Boot project
1. Use the postman collection on the *resources/postman* folder to test the API

If you want to access the mongo container, use the command:

```
docker exec -it mongo mongosh -u root --authenticationDatabase admin
```

&nbsp;

## **Dependencies**

- Docker
- Docker compose
- Java JDK 17
- Maven 3.9.3+