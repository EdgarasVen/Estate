# Estate

Simple CRUD application.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `src\main\java\lt\estate\app\AppApplication.java` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Running the application from emty folder

Open PowerShell.

Create new folder.
```shell
mkdir project
```

Go inside.
```shell
cd .\project\
```

Download all files.
```shell
git clone https://github.com/EdgarasVen/Estate.git
```

Go inside.
```shell
cd .\Estate\
```

Run spring boot.
```shell
mvn spring-boot:run 
```

## H2 and Swagger

H2 url: http://localhost:8080/h2/
Swagger url: http://localhost:8080/swagger-ui.html#/


## Other information

Full work with all commits to check go to [My repository](https://github.com/EdgarasVen/Projects/tree/master/estate/app)
