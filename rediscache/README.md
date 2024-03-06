<h1 align="center"><strong>Redis Cache</strong></h1>

This project is an example of implementing Redis Cache.

In this example, the cache was implemented in the Service class. This is because it is only possible to create cache from serializable objects. The Controller class returns the ResponseEntity object which is not serializable. This is why caching was added to the Service class methods, considering that they return serializable objects.

The following notations were used in this project:

- @Cacheable: Stores and fetches values present in the Redis database
- @CachePut: Stores and updates values in the Redis database
- @CacheEvict: Deletes values in the Redis database

The findById method of the UserService class has the @Cachable notation. This causes non-null values that are returned by the method to be stored in Redis. If the queried value already exists, Redis immediately returns the value, without the need to execute the code within the method.

&nbsp;

## **Dependencies**

- Java JDK 17
- Maven 3.9.3+