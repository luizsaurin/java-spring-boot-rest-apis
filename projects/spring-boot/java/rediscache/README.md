<h1 align="center"><strong>Redis Cache</strong></h1>

<p>This project is an example of implementing Redis Cache.</p>

<p>In this example, the cache was implemented in the Service class. This is because it is only possible to create cache from serializable objects. The Controller class returns the ResponseEntity object which is not serializable. This is why caching was added to the Service class methods, considering that they return serializable objects.</p>

<p>The following notations were used in this project:</p>

- @Cacheable: Stores and fetches values present in the Redis database
- @CachePut: Stores and updates values in the Redis database
- @CacheEvict: Exclui valores no banco de dados do Redis

<p>O método findById da classe UserService possui a notação @Cachable. Isso faz com que os valores não nulos que são retornados pelo método sejam armazenados no Redis. Se o valor consultado já existe, o Redis já retorna imediadamente o valor, sem a necessidade de realizar o código dentro do método.</p>
