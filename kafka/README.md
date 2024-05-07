<h1 align="center"><strong>Kafka</strong></h1>

This is an example of a Java Spring Boot API using Apache Kafka messaging to produce and consume messages. Kafka is a very powerful and robust system, and goes beyond just transmitting messages. However, the purpose of this project is just to implement a simple producer and consumer of messages.

&nbsp;

## Docker

A docker-compose is available to run Kafka more easily. Because it is a small project, compose only has one Kafka container and one zookeeper container, but there can be several instances of these services at the same time.

The compose file also has a control-center image, which provides a graphical dashboard to facilitate Kafka monitoring. By default it is commented, but it is there if you are interested in learning about the tool. Control-center initialization takes some time, so wait a few seconds/minutes until the service is accessible.

&nbsp;

## Hosts

When running docker with kafka services and the spring boot application on localhost, the spring api may not be able to find the kafka services, precisely because they are on different networks. To do this, add the following configuration to your machine's hosts:

```
127.0.0.1 localhost kafka zookeeper
```

This way, the Kafka and Zookeeper services will be visible to the Spring API running on localhost.

&nbsp;

## Producer's first run

It is possible that when sending a message for the first time using the producer implemented in the Spring API, an error occurs indicating the message: LEADER_NOT_AVAILABLE.

This means that Kafka did not identify which is the leader partition present on the broker server. Either way, Kafka will automatically set the leader partition, and it is expected that subsequent messages will be sent successfully.

&nbsp;

## Useful commands

It is possible to monitor Kafka's operation through the control center. However, if there is a need to access via terminal, below are some useful commands.

### Search topic

If you want to check whether a certain topic is registered, use the following command:

```
kafka-topics --list --bootstrap-server localhost:9092 | grep "hello-topic"
```

In this case, a search will be made for topics that have the identifier "hello-topic".

### Create consumer

If you want to monitor whether messages from a specific topic are reaching Kafka, use the following command:

```
kafka-console-consumer --bootstrap-server localhost:9092 --topic hello-topic --from-beginning
```

The *--from-beginning* flag is optional, displaying all messages since the topic was created.

### Search group

If you want to check whether a certain consumer group was created, use the following command:

```
kafka-consumer-groups --bootstrap-server localhost:9092 --list | grep "group-01"
```

&nbsp;

## How to run

1. Run the docker-compose
1. Run the spring API
1. Use the postman collection to send messages. Location: *resources/postman*

## Dependencies

- Docker
- Docker compose
- Java JDK 17
- Maven 3.9.3+