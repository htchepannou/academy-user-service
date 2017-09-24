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

### Run the server locally
```
$ java -Dspring.profiles.active=local -jar target/academy-user-service.jar
```
The server will run locally on the port `18081`
- Verify the status of the service at [http://localhost:18081/health](http://localhost:18081/health). The status should be `UP`. 
- Access the API documentation at [http://localhost:18081/swagger-ui.html](http://localhost:18081/swagger-ui.html) 

## License
This project is open source sofware under the [MIT License](https://opensource.org/licenses/MIT)
