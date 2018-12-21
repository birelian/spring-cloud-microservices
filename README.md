# Spring Cloud Microservices PoC

This project is a proof of concept of how to develop a simple microservices architecture oriented application using
*Spring Cloud Netflix*.

The application is formed by this set of components:

 - `discovery-service`: A discovery service implemented with *Spring Eureka Server*. Every microservice instance registers here
when it bootstraps.
 - `config-server`: A configuration server implemented with *Spring Config Server*.
 - `api-gateway`: An API gateway that routes all requests. Implemented with *Spring Zuul*.
 - `time-service`: A simple service that gives the local date and time and prints the port where the service is listening. 
 - `greeting-service`: A simple service that says hello and prints the port where the service is listening. The greeting message
is read from the `config-server`. The service also calls `time-service` to print the date and time when saying hello.
 - `admin-service`: A *Spring Admin Server* used to get info about all the microservices. Please see
[the documentation](https://github.com/codecentric/spring-boot-admin) for further information. Thanks to
[Codecentric](https://blog.codecentric.de/) for such a great tool!

# Prerequisites

 - *Java* and *Maven*: In order to compile the services.
 - *Docker*(optional): In order to run all the services in a simple way. [Get it from here](https://docs.docker.com/)

# How to run

First of all you must compile the services. It also generates the required *Docker* images:

```
$ mvn clean install
```

There are two docker compose files:

 - `core-services-compose.yml`: It sets up the following components:
    - 1 instance of `discovery-service` at port `8761`.
    - 1 instance of `config-service` at port `8888`.
 - `satellite-services-compose.yml`: It sets up the following components:
    - 1 instance of `api-gateway` at port `8000`.
    - 2 instances of `time-service` at ports `7001` and `7002`.
    - 2 instances of `greeting-service` at ports `8001` and `8002`.
    - 1 instance of `admin-service` at port `8080`.

So, the steps to set the whole thing up:
```
$ docker-compose -f core-services-compose.yml up
```
After this, go to http://localhost:8761 and keep refreshing until you can see that the `config-service` has registered.

Start the rest of services:
```
$ docker-compose -f satellite-services-compose.yml up
```

Go to http://localhost:8761 and keep refreshing until you can see that all instances have registered.

# What now?

First of all, hit here and keep refreshing until you can see the routes to `config-service`, `time-service` and `greeting-service`:
 - http://localhost:8000/actuator/routes

You can go to the `time-service` with no load balancing hitting here:
 - http://localhost:7001
 - http://localhost:7002

Now, try to go through the `api-gateway`service in a load balanced way. See how port changes when you refresh the browser several
times:
 - http://localhost:8000/time-service

The same can be done with `greeting-service`. Go here to hit with no load balancing:
 - http://localhost:8001
 - http://localhost:8002

Now, try to go through the `api-gateway`service in a load balanced way:
 - http://localhost:8000/greeting-service
 
You may also see how the properties that `config-service` is serving to `greeting-service`:
 - http://localhost:8888/greeting-service/default
 

 
And finally, you may want to have a look to the `admin-service`:
 - http://localhost:8080