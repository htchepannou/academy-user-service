[![Build Status](https://travis-ci.org/htchepannou/academy-user-service.svg?branch=master)](https://travis-ci.org/htchepannou/academy-user-service)
[![Code Coverage](https://img.shields.io/codecov/c/github/htchepannou/academy-user-service/master.svg)](https://codecov.io/github/htchepannou/academy-user-service?branch=master)
[![JDK](https://img.shields.io/badge/jdk-1.8-brightgreen.svg)](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)

# Academy User Service
Academy User Service provide a REST API for managing the academy users.

## Requirements
- Java 1.8
- Maven
- MySQL

## Installation
Initialize the local database
```
$ mysql -uroot
...
mysql> CREATE DATABASE academy_user;
```

Clone the code repository locally and build it.
```
$ git clone git@github.com:htchepannou/academy-user-service.git
$ cd academy-user-service
$ mvn clean install
```

This will generate the service binary ``target/academy-user-service.jar``

### Run the server
```
$ java -jar target/academy-user-service.jar
```

## Links
- Local Environment
    - [API Documentation](http://localhost:8080/swagger-ui.html) 
    - [Service Health](http://localhost:8080/health) 

- Integration Environment
    - [API Documentation](https://io-tchepannou-a-user-service.herokuapp.com/swagger-ui.html) 
    - [Service Health](https://io-tchepannou-a-user-service.herokuapp.com/health) 

## License
This project is open source sofware under the [MIT License](https://opensource.org/licenses/MIT)
